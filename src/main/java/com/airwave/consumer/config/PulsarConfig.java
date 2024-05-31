package com.airwave.consumer.config;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfig {

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException, PulsarClientException {
        return PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
    }
}
