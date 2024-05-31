package com.airwave.consumer.service;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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



    private Consumer<byte[]> consumer;

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
                            log.info("Message Received: " + message);

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


}
