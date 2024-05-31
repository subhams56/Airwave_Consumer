package com.airwave.consumer.model;

import java.util.Date;

import jakarta.persistence.Column;
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

    @Column(name = "geofence_id")
    private Integer geofenceId;

    @Id
    @Column(name = "viewname")
    private String viewname;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "date")
    private Date date;

    @Column(name = "region")
    private String region;

    @Column(name = "insertion")
    private Date insertion;

    @Column(name = "screen")
    private String screen;

    @Column(name = "module")
    private String module;

    @Column(name = "project")
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
