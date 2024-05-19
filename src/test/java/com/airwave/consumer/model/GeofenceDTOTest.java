package com.airwave.consumer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GeofenceDTOTest {

    private GeofenceDTO geofenceDTO;

    @Mock
    private Date mockDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        geofenceDTO = new GeofenceDTO();
    }

    @Test
    void testGetGeofenceId() {
        geofenceDTO.setGeofenceId(1);
        assertEquals(1, geofenceDTO.getGeofenceId());
    }

    @Test
    void testSetGeofenceId() {
        geofenceDTO.setGeofenceId(1);
        assertEquals(1, geofenceDTO.getGeofenceId());
    }

    @Test
    void testGetViewname() {
        String viewname = "testView";
        geofenceDTO.setViewname(viewname);
        assertEquals(viewname, geofenceDTO.getViewname());
    }

    @Test
    void testSetViewname() {
        String viewname = "testView";
        geofenceDTO.setViewname(viewname);
        assertEquals(viewname, geofenceDTO.getViewname());
    }

    @Test
    void testGetType() {
        String type = "testType";
        geofenceDTO.setType(type);
        assertEquals(type, geofenceDTO.getType());
    }

    @Test
    void testSetType() {
        String type = "testType";
        geofenceDTO.setType(type);
        assertEquals(type, geofenceDTO.getType());
    }

    @Test
    void testIsStatus() {
        geofenceDTO.setStatus(true);
        assertEquals(true, geofenceDTO.isStatus());
    }

    @Test
    void testSetStatus() {
        geofenceDTO.setStatus(true);
        assertEquals(true, geofenceDTO.isStatus());
    }

    @Test
    void testGetDate() {
        geofenceDTO.setDate(mockDate);
        assertEquals(mockDate, geofenceDTO.getDate());
    }

    @Test
    void testSetDate() {
        geofenceDTO.setDate(mockDate);
        assertEquals(mockDate, geofenceDTO.getDate());
    }

    @Test
    void testToString() {
        geofenceDTO.setGeofenceId(1);
        geofenceDTO.setViewname("testView");
        geofenceDTO.setType("testType");
        geofenceDTO.setStatus(true);
        geofenceDTO.setDate(mockDate);

        String expected = "Geofence{geofence_id=1, viewname='testView', type='testType', status=true, date=" + mockDate.toString() + "}";
        assertEquals(expected, geofenceDTO.toString());
    }
}