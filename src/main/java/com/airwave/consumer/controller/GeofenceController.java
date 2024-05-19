package com.airwave.consumer.controller;

import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import com.airwave.consumer.service.GeofenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public GeofenceController(GeofenceService geofenceService){
        this.geofenceService = geofenceService;
    }

    @RequestMapping("/get")
    public Map<String, List<GeofenceDTO>>  getGeofenceRecords(){
       return geofenceService.getGeofenceRecords();

    }


    @GetMapping("/getGeofenceAsList")
    public List<?> getRecords() throws Exception {

        return geofenceService.getRecords();


    }

    @GetMapping("/getConvertedGeofence")
    public List<?> getConvertedGeofence() throws Exception {

       List<GeofenceDTO> geofenceDTOS = geofenceService.getRecords();
       return geofenceService.convertToGeofence(geofenceDTOS);


    }

    @GetMapping("/saveGeofence")
    public void saveGeofenceRecords() throws Exception {

        List<GeofenceDTO> geofenceDTOS = geofenceService.getRecords();
        List<GeofenceRecords> geofenceRecords =geofenceService.convertToGeofence(geofenceDTOS);
        int geoFenceListSize = geofenceRecords.size();
        logger.info("Geofence List Size : "+geoFenceListSize);

        geofenceService.saveGeofenceRecords(geofenceRecords);


    }





}