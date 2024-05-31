package com.airwave.consumer.controller;

import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import com.airwave.consumer.service.GeofencePulsarListener;
import com.airwave.consumer.service.GeofenceService;
import jakarta.mail.MessagingException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/geofence")
public class GeofenceController {

    private static final Logger logger = LoggerFactory.getLogger(GeofenceController.class);

    private GeofenceService geofenceService;


    @Autowired
    private GeofencePulsarListener geofencePulsarListener;

    public GeofenceController(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;

    }

    @RequestMapping("/get")
    public Map<String, List<GeofenceDTO>>  getGeofenceRecords(){
       return geofenceService.getGeofenceRecords();

    }


    @GetMapping("/getGeofenceAsList")
    public List<GeofenceDTO> getRecords()  {

        return geofenceService.getRecords();


    }

    @GetMapping("/getConvertedGeofence")
    public List<GeofenceRecords> getConvertedGeofence()  {

       List<GeofenceDTO> geofenceDTOS = geofenceService.getRecords();
       return geofenceService.convertToGeofence(geofenceDTOS);


    }

    @GetMapping("/saveGeofence")
    public void saveGeofenceRecords() throws MessagingException {

        List<GeofenceDTO> geofenceDTOS = geofenceService.getRecords();
        List<GeofenceRecords> geofenceRecords =geofenceService.convertToGeofence(geofenceDTOS);
        int geoFenceListSize = geofenceRecords.size();
        logger.info("Geofence List Size : {}", geoFenceListSize);

        geofenceService.saveGeofenceRecords(geofenceRecords);


    }

    @GetMapping("/listenTopic")
    public ResponseEntity<String> startListener() {
        try {
            geofencePulsarListener.startListener();
            return ResponseEntity.ok("Pulsar listener started.");
        } catch (PulsarClientException e) {
            return ResponseEntity.status(500).body("Failed to start Pulsar listener: " + e.getMessage());
        }
    }

    @GetMapping("/abortTopic")
    public ResponseEntity<String> stopListener() {
        geofencePulsarListener.stopListener();
        return ResponseEntity.ok("Pulsar listener stopped.");
    }





}
