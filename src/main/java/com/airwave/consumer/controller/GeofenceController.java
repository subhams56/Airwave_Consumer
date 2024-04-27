package com.airwave.consumer.controller;

import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.service.GeofenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/geofence")
public class GeofenceController {

    private GeofenceService geofenceService;

    public GeofenceController(GeofenceService geofenceService){
        this.geofenceService = geofenceService;
    }

    @RequestMapping("/get")
    public Map<String, List<GeofenceDTO>>  getGeofenceRecords(){
       return geofenceService.getGeofenceRecords();
    }
}
