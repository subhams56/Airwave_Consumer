package com.airwave.consumer.model;

import java.util.Date;

public class GeofenceDTO {
    private int geofence_id;
    private String viewname;
    private String type;
    private boolean status;
    private Date date;

    // Constructors
    public GeofenceDTO() {
    }

    public GeofenceDTO(int geofence_id, String viewname, String type, boolean status, Date date) {
        this.geofence_id = geofence_id;
        this.viewname = viewname;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    // Getters and Setters
    public int getGeofence_id() {
        return geofence_id;
    }

    public void setGeofence_id(int geofence_id) {
        this.geofence_id = geofence_id;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Geofence{" +
                "geofence_id=" + geofence_id +
                ", viewname='" + viewname + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
