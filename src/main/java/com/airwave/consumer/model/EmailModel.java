package com.airwave.consumer.model;

import java.util.Date;

public class EmailModel {

    private Date date;
    private String appName;

    private int recordsCount;
    private Boolean status;

    private String hostName;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "date=" + date +
                ", appName='" + appName + '\'' +
                ", recordsCount=" + recordsCount +
                ", status=" + status +
                ", hostName='" + hostName + '\'' +
                '}';
    }
}
