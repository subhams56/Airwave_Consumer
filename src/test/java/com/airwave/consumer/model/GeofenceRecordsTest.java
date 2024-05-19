package com.airwave.consumer.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class GeofenceRecordsTest {

    @Test
    void testConstructor() {
        Integer geofenceId = 1;
        String viewname = "viewname";
        String type = "type";
        Boolean status = true;
        Date date = new Date();
        String region = "region";
        Date insertion = new Date();
        String screen = "screen";
        String module = "module";
        String project = "project";

        GeofenceRecords geofenceRecords = new GeofenceRecords(geofenceId, viewname, type, status, date, region, insertion, screen, module, project);

        assertEquals(geofenceId, geofenceRecords.getGeofenceId());
        assertEquals(viewname, geofenceRecords.getViewname());
        assertEquals(type, geofenceRecords.getType());
        assertEquals(status, geofenceRecords.getStatus());
        assertEquals(date, geofenceRecords.getDate());
        assertEquals(region, geofenceRecords.getRegion());
        assertEquals(insertion, geofenceRecords.getInsertion());
        assertEquals(screen, geofenceRecords.getScreen());
        assertEquals(module, geofenceRecords.getModule());
        assertEquals(project, geofenceRecords.getProject());
    }

    @Test
    void testSettersAndGetters() {
        GeofenceRecords geofenceRecords = new GeofenceRecords();

        Integer geofenceId = 1;
        geofenceRecords.setGeofenceId(geofenceId);
        assertEquals(geofenceId, geofenceRecords.getGeofenceId());

        String viewname = "viewname";
        geofenceRecords.setViewname(viewname);
        assertEquals(viewname, geofenceRecords.getViewname());

        String type = "type";
        geofenceRecords.setType(type);
        assertEquals(type, geofenceRecords.getType());

        Boolean status = true;
        geofenceRecords.setStatus(status);
        assertEquals(status, geofenceRecords.getStatus());

        Date date = new Date();
        geofenceRecords.setDate(date);
        assertEquals(date, geofenceRecords.getDate());

        String region = "region";
        geofenceRecords.setRegion(region);
        assertEquals(region, geofenceRecords.getRegion());

        Date insertion = new Date();
        geofenceRecords.setInsertion(insertion);
        assertEquals(insertion, geofenceRecords.getInsertion());

        String screen = "screen";
        geofenceRecords.setScreen(screen);
        assertEquals(screen, geofenceRecords.getScreen());

        String module = "module";
        geofenceRecords.setModule(module);
        assertEquals(module, geofenceRecords.getModule());

        String project = "project";
        geofenceRecords.setProject(project);
        assertEquals(project, geofenceRecords.getProject());
    }
}