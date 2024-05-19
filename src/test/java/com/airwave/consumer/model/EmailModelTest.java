package com.airwave.consumer.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailModelTest {

    @Test
    void testGetDate() {
        Date date = new Date();
        EmailModel emailModel = new EmailModel();
        emailModel.setDate(date);
        assertEquals(date, emailModel.getDate());
    }

    @Test
     void testSetDate() {
        Date date = new Date();
        EmailModel emailModel = new EmailModel();
        emailModel.setDate(date);
        assertEquals(date, emailModel.getDate());
    }

    @Test
     void testGetAppName() {
        String appName = "Test App";
        EmailModel emailModel = new EmailModel();
        emailModel.setAppName(appName);
        assertEquals(appName, emailModel.getAppName());
    }

    @Test
     void testSetAppName() {
        String appName = "Test App";
        EmailModel emailModel = new EmailModel();
        emailModel.setAppName(appName);
        assertEquals(appName, emailModel.getAppName());
    }

    @Test
     void testGetRecordsCount() {
        int recordsCount = 10;
        EmailModel emailModel = new EmailModel();
        emailModel.setRecordsCount(recordsCount);
        assertEquals(recordsCount, emailModel.getRecordsCount());
    }

    @Test
     void testSetRecordsCount() {
        int recordsCount = 10;
        EmailModel emailModel = new EmailModel();
        emailModel.setRecordsCount(recordsCount);
        assertEquals(recordsCount, emailModel.getRecordsCount());
    }

    @Test
     void testGetStatus() {
        Boolean status = true;
        EmailModel emailModel = new EmailModel();
        emailModel.setStatus(status);
        assertEquals(status, emailModel.getStatus());
    }

    @Test
     void testSetStatus() {
        Boolean status = true;
        EmailModel emailModel = new EmailModel();
        emailModel.setStatus(status);
        assertEquals(status, emailModel.getStatus());
    }

    @Test
     void testGetHostName() {
        String hostName = "localhost";
        EmailModel emailModel = new EmailModel();
        emailModel.setHostName(hostName);
        assertEquals(hostName, emailModel.getHostName());
    }

    @Test
     void testSetHostName() {
        String hostName = "localhost";
        EmailModel emailModel = new EmailModel();
        emailModel.setHostName(hostName);
        assertEquals(hostName, emailModel.getHostName());
    }

    @Test
     void testToString() {
        Date date = new Date();
        String appName = "Test App";
        int recordsCount = 10;
        Boolean status = true;
        String hostName = "localhost";
        EmailModel emailModel = new EmailModel();
        emailModel.setDate(date);
        emailModel.setAppName(appName);
        emailModel.setRecordsCount(recordsCount);
        emailModel.setStatus(status);
        emailModel.setHostName(hostName);
        String expected = "EmailModel{" +
                "date=" + date +
                ", appName='" + appName + '\'' +
                ", recordsCount=" + recordsCount +
                ", status=" + status +
                ", hostName='" + hostName + '\'' +
                '}';
        assertEquals(expected, emailModel.toString());
    }
}