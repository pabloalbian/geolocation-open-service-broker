package com.example.geolocationopenservicebroker.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeolocationServiceInstance {
    private final String instanceId;
    private final String serviceDefinitionId;
    private final String planId;
    private final String dashboardUrl;
}
