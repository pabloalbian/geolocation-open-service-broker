package com.example.geolocationopenservicebroker.config;

import org.springframework.cloud.servicebroker.model.catalog.Catalog;
import org.springframework.cloud.servicebroker.model.catalog.Plan;
import org.springframework.cloud.servicebroker.model.catalog.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceCatalogConfiguration {

    @Bean
    public Catalog catalog() {
        Plan geolocationBasicPlan = Plan.builder()
                .id("f5e235c5-aaed-44e1-aaea-5c7bac221294")
                .name("geolocation-basic-plan")
                .description("Geolocation Basic Plan")
                .free(true)
                .build();

        ServiceDefinition serviceDefinition = ServiceDefinition.builder()
                .id("6ca20b8a-a182-48a6-ab8e-bc462a2af1d1")
                .name("geolocation-service")
                .description("Geolocation Service")
                .bindable(true)
                .tags("geolocation", "service")
                .plans(geolocationBasicPlan)
                .metadata("displayName", "geolocation-service")
                .metadata("longDescription", "Coordinates and geolocation provider service")
                .metadata("providerDisplayName", "Geolocation GmbH")
                .build();

        return Catalog.builder()
                .serviceDefinitions(serviceDefinition)
                .build();
    }
}
