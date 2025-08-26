package com.acme.auctionhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class ScheduledConfig {

    private final ScheduledProperties scheduledProperties;

    public ScheduledConfig(ScheduledProperties scheduledProperties) {
        this.scheduledProperties = scheduledProperties;
    }

    public ScheduledProperties getScheduledProperties() {
        return scheduledProperties;
    }

}
