package com.airwave.consumer.service;

import aj.org.objectweb.asm.TypeReference;
import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import com.airwave.consumer.repository.GeofenceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.data.auditing.CurrentDateTimeProvider.*;

@Service
@Transactional
public class GeofenceService {

    private static final Logger logger = LoggerFactory.getLogger(GeofenceService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Value( "${airwave.geofence.url}")
    private String AirwaveUrl;



    private final ObjectMapper objectMapper = new ObjectMapper();





    public void saveGeofenceRecords(List<GeofenceRecords> geofenceRecords){

        logger.info("Saving Geofence Records to DB");
        try{
            geofenceRepository.saveAll(geofenceRecords);

        }
        catch (Exception e){
            logger.error("Error saving Geofence Records to DB");
        }


    }


    public Map<String, List<GeofenceDTO>> getGeofenceRecords() {
    String url = AirwaveUrl;
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    HttpEntity<Map<String, List<GeofenceDTO>>> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, List<GeofenceDTO>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,

                new ParameterizedTypeReference<Map<String, List<GeofenceDTO>>>() {}
        );

        logger.info("Records:{} " , responseEntity.getBody().keySet() );
        return responseEntity.getBody();

    }

//    public void retrieveAndProcessGeofences() throws Exception {
//        String url = AirwaveUrl;
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            String responseBody = response.getBody();
//            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
//
//            List<GeofenceDTO> geofences = new ArrayList<>();
//            for (Object obj : responseMap.get("Size : 10000")) {
//                if (obj instanceof LinkedHashMap) {
//                    GeofenceDTO geofence = (GeofenceDTO) obj; // Cast each object to GeofenceDTO
//                    geofences.add(geofence);
//                } else {
//                    // Handle unexpected object types (optional)
//                }
//            }
//
//            for (GeofenceDTO geofence : geofences) {
//                GeofenceRecords processedResult = processGeofenceRecord(geofence);
//                logger.info("Processed geofence (id: {}): {}", geofence.getGeofence_id(), processedResult);
//            }
//        } else {
//            logger.error("API call failed with status code: {}", response.getStatusCode());
//            // Handle error scenarios (e.g., throw exception)
//        }
//    }

    public List<GeofenceDTO> getRecords() {
        String url = AirwaveUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, List<GeofenceDTO>>> entity = new HttpEntity<>(headers);

        ResponseEntity<Map<String, List<GeofenceDTO>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,

                new ParameterizedTypeReference<Map<String, List<GeofenceDTO>>>() {}
        );

//        logger.info("Records:{} " , responseEntity.getBody().entrySet());
        return responseEntity.getBody().entrySet().iterator().next().getValue();




    }

    public List<GeofenceRecords> convertToGeofence(List<GeofenceDTO> geofenceDTOList)
    {
        logger.info("Converting to Geofence");
        List<GeofenceRecords> convertedGeofenceRecords = new ArrayList<>();

        for(GeofenceDTO geofenceDTO : geofenceDTOList){
            GeofenceRecords geofenceRecords = processGeofenceRecord(geofenceDTO);
            convertedGeofenceRecords.add(geofenceRecords);
//            logger.info("Processed geofence (id: {}): {}", geofenceDTO.getGeofence_id(), geofenceRecords);
        }

        return convertedGeofenceRecords;
    }


//    public void getRecords() {
//        String url = AirwaveUrl;
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//        HttpEntity<Map<String, List<GeofenceDTO>>> entity = new HttpEntity<>(headers);
//        ResponseEntity<Map<String, List<GeofenceDTO>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, List<GeofenceDTO>>>() {});
//
//        for (Map.Entry<String, List<GeofenceDTO>> entry : responseEntity.getBody().entrySet()) {
//            if (entry.getKey() instanceof String) {
//                logger.info("Records: {}", entry.getValue());
//                break; // Assuming you only want to log the first key-value pair with a String key
//            }
//        }
//    }







    public GeofenceRecords processGeofenceRecord(GeofenceDTO geofenceDTO) {

        GeofenceRecords geofenceRecords = new GeofenceRecords();

        geofenceRecords.setGeofence_id(geofenceDTO.getGeofence_id());
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
