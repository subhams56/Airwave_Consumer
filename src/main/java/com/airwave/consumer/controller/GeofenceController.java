package com.airwave.consumer.controller;

import com.airwave.consumer.service.GeofenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geofence")
public class GeofenceController {

    private GeofenceService geofenceService;

    public GeofenceController(GeofenceService geofenceService){
        this.geofenceService = geofenceService;
    }

    @RequestMapping("/get")
    public String getGeofenceRecords(){
        return geofenceService.getGeofenceRecords().toString();
    }
}
