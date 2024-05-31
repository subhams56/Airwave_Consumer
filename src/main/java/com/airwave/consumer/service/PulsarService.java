package com.airwave.consumer.service;


import com.airwave.consumer.model.GeofenceDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class PulsarService {

    public GeofenceDTO convertJsonToGeofenceDTO(String json) {

            JSONObject jsonObject = new JSONObject(json);
            GeofenceDTO geofenceDTO = new GeofenceDTO();
            geofenceDTO.setGeofenceId(jsonObject.getInt("geofenceId"));
            geofenceDTO.setViewname(jsonObject.getString("viewname"));
            geofenceDTO.setType(jsonObject.getString("type"));
            geofenceDTO.setStatus(jsonObject.getBoolean("status"));
            geofenceDTO.setDate(new Date(jsonObject.getLong("date")));
            return geofenceDTO;

    }
}
