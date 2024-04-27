package com.airwave.consumer.service;

import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GeofenceService {

    @Autowired
    private RestTemplate restTemplate;

    @Value( "${airwave.geofence.url}")
    private String AirwaveUrl;





    public Map<String, List<GeofenceDTO>> getGeofenceRecords() {
    String url = AirwaveUrl;
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<Map<String, List<GeofenceDTO>>> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<Map<String, List<GeofenceDTO>>>() {}
    );

    return response.getBody();
}



}
