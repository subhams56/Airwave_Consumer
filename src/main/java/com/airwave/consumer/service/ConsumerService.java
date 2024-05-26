package com.airwave.consumer.service;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Value("${pulsar.topic.service.url}")
    private String serviceUrl;

    @Value("${pulsar.topic.name}")
    private String topicName;

    @Value("${pulsar.topic.subscription.name}")
    private String subscriptionName;

    private PulsarClient client = null;
    private Consumer<byte[]> consumerGeofence = null;

    public void listenTopic() throws PulsarClientException {
        logger.info("Pulsar Geofence Service URL: {}", serviceUrl);
        logger.info("Geofence topic: {}", topicName);

        if (consumerGeofence == null) {
            consumerGeofence = buildConsumer(serviceUrl, createListener("GEOFENCE"));
            logger.info("Listener Created for : {}", topicName);
        } else {
            logger.warn("Consumer already initialized");
        }
    }

    public MessageListener<byte[]> createListener(String node) {
        return (consumer, msg) -> {
            try {
                String message = new String(msg.getData());
                logger.debug("{}: Received message: {}", node, message);
                consumer.acknowledge(msg);
            } catch (Exception e) {
                logger.error("{}: Unexpected exception in consumer", node, e);
                consumer.negativeAcknowledge(msg);
            }
        };
    }

    public Consumer<byte[]> buildConsumer(String serviceUrl, MessageListener<byte[]> listener) throws PulsarClientException {
        logger.info("Creating Pulsar client...");
        client = PulsarClient.builder().serviceUrl(serviceUrl).build();
        logger.info("Client created. Subscribing to topic: {}, subscription: {}", topicName, subscriptionName);

        return client.newConsumer()
                .topic(topicName)
                .subscriptionName(subscriptionName)
                .subscriptionType(SubscriptionType.Shared)
                .messageListener(listener)
                .subscribe();
    }

    // Optional: Shutdown hook to close Pulsar client on application shutdown
    @PreDestroy
    public void cleanup() {
        try {
            if (consumerGeofence != null) {
                consumerGeofence.close();
            }
            if (client != null) {
                client.close();
            }
        } catch (PulsarClientException e) {
            logger.error("Error closing Pulsar client or consumer", e);
        }
    }
}
