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
@SuppressWarnings("unused")
public class GeofenceRecords {


    private Integer geofenceId;
    @Id
    private String viewname;
    private String type;
    private Boolean status;
    private Date date;
    private String region;
    private Date insertion;
    private String screen;
    private String module;
    private String project;


    public GeofenceRecords(Integer geofenceId, String viewname, String type, Boolean status, Date date, String region, Date insertion, String screen, String module, String project) {
        this.geofenceId = geofenceId;
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
