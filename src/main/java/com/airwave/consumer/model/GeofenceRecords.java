package com.airwave.consumer.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GeofenceRecords")
@Data
@NoArgsConstructor
public class GeofenceRecords {

    @Id
    private Integer geofence_id;

    private String viewname;
    private String type;
    private Boolean status;
    private Date date;
    private String region;
    private Date insertion;
    private String screen;
    private String module;
    private String project;

    // Constructor with all fields except ID
    public GeofenceRecords(String viewname, String type, Boolean status, Date date, String region, Date insertion, String screen, String module, String project) {
        this.viewname = viewname;
        this.type = type;
        this.status = status;
        this.date = date;
        this.region = region;
        this.insertion = insertion;
        this.screen = screen;
        this.module = module;
        this.project = project;
    }
}
