package com.airwave.consumer.service;


import com.airwave.consumer.model.GeofenceDTO;
import com.airwave.consumer.model.GeofenceRecords;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeofencePulsarListener {

    @Value("${pulsar.topic.service.url}")
    private String serviceUrl;

    @Value("${pulsar.topic.name}")
    private String topicName;

    @Value("${pulsar.topic.subscription.name}")
    private String subscriptionName;


    @Autowired
    private PulsarClient pulsarClient;

    @Autowired
    private PulsarService pulsarService;

    @Autowired
    private GeofenceService geofenceService;



    private Consumer<byte[]> consumer;

    List<GeofenceRecords> geofenceRecords = new ArrayList<>();

    public void startListener() throws PulsarClientException {
        if (consumer == null) {
            consumer = pulsarClient.newConsumer()
                    .topic(topicName)
                    .subscriptionName(subscriptionName)
                    .subscriptionType(SubscriptionType.Shared)
                    .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                    .messageListener((consumer1, msg) -> {
                        try {
                            String message = new String(msg.getData());
                            log.info("Received Geofences From Topic");
                            if(isJsonObject(message)){
                                GeofenceDTO geofenceDTO = pulsarService.convertJsonToGeofenceDTO(message);
                                geofenceRecords.add(geofenceService.processGeofenceRecord(geofenceDTO));
                                log.info("Total Geofence Records to be saved: {}", geofenceRecords.size());
                                geofenceService.saveGeofenceRecords(geofenceRecords);
                            }



                            consumer1.acknowledge(msg);

                        } catch (Exception e) {
                            e.printStackTrace();
                            consumer1.negativeAcknowledge(msg);
                        }
                    })
                    .subscribe();
            log.info("Pulsar listener started.");
        } else {
            log.info("Pulsar listener is already running.");
        }
    }

    public void stopListener() {
        if (consumer != null) {
            try {
                consumer.close();
                consumer = null;
                log.info("Pulsar listener stopped.");
            } catch (PulsarClientException e) {
                e.printStackTrace();
                log.error("Failed to stop Pulsar listener.", e);
            }
        } else {
            log.info("Pulsar listener is not running.");
        }
    }


    public boolean isJsonObject(String json){
        try{

            JSONObject jsonObject = new JSONObject(json);
            return true;
        }catch(Exception e){
            return false;
        }
    }


}
