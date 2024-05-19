package com.airwave.consumer.service;

import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import com.airwave.consumer.repository.GeofenceRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;



@Service
@Transactional
public class GeofenceService {

    private static final Logger logger = LoggerFactory.getLogger(GeofenceService.class);


    private RestTemplate restTemplate;
    private EmailService emailService;
    private GeofenceRepository geofenceRepository;

    @Autowired
    public GeofenceService(RestTemplate restTemplate, EmailService emailService, GeofenceRepository geofenceRepository) {
        this.restTemplate = restTemplate;
        this.emailService = emailService;
        this.geofenceRepository = geofenceRepository;
    }

    @Value( "${airwave.geofence.url}")
    private String airwaveUrl;







    public void saveGeofenceRecords(List<GeofenceRecords> geofenceRecords) throws MessagingException {

        logger.info("Saving Geofence Records to DB");
        int recordCount = 0;
        try {
            geofenceRepository.saveAll(geofenceRecords);
            recordCount = geofenceRecords.size();
            emailService.sendEmail(recordCount, true);


        } catch (Exception e) {
            logger.error("Error saving Geofence Records to DB");
            emailService.sendEmail(recordCount, false);
        }


    }


    public Map<String, List<GeofenceDTO>> getGeofenceRecords() {
        String url = airwaveUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, List<GeofenceDTO>>> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, List<GeofenceDTO>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        Map<String, List<GeofenceDTO>> responseBody = responseEntity.getBody();

        if (responseBody == null) {
            logger.warn("Received null response body from {}", url);
            return Collections.emptyMap();
        }

        logger.info("Records: {}", responseBody.keySet());
        return responseBody;
    }





    public List<GeofenceDTO> getRecords() {
        String url = airwaveUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, List<GeofenceDTO>>> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, List<GeofenceDTO>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,

                new ParameterizedTypeReference<Map<String, List<GeofenceDTO>>>() {}
        );


        return Objects.requireNonNull(responseEntity.getBody()).entrySet().iterator().next().getValue();




    }

    public List<GeofenceRecords> convertToGeofence(List<GeofenceDTO> geofenceDTOList)
    {
        logger.info("Converting to Geofence");
        List<GeofenceRecords> convertedGeofenceRecords = new ArrayList<>();

        for(GeofenceDTO geofenceDTO : geofenceDTOList){
            GeofenceRecords geofenceRecords = processGeofenceRecord(geofenceDTO);
            convertedGeofenceRecords.add(geofenceRecords);

        }

        return convertedGeofenceRecords;
    }









    public GeofenceRecords processGeofenceRecord(GeofenceDTO geofenceDTO) {

        GeofenceRecords geofenceRecords = new GeofenceRecords();

        geofenceRecords.setGeofenceId(geofenceDTO.getGeofenceId());
        geofenceRecords.setViewname(geofenceDTO.getViewname()+"_"+ UUID.randomUUID());
        geofenceRecords.setType(geofenceDTO.getType());
        geofenceRecords.setStatus(geofenceDTO.isStatus());
        geofenceRecords.setDate(geofenceDTO.getDate());
        geofenceRecords.setRegion("AIR-US1");
        geofenceRecords.setInsertion(new Date());
        geofenceRecords.setScreen("WIN/IOS");
        geofenceRecords.setModule("AirwaveUSA");
        geofenceRecords.setProject("Airwave-Consumer");

        logger.info("Modified Geofence Record: {}", geofenceRecords);

        return geofenceRecords;

    }





}
