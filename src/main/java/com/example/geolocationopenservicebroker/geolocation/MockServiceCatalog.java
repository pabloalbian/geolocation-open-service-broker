package com.example.geolocationopenservicebroker.geolocation;

import com.example.geolocationopenservicebroker.model.MaintenanceInfo;
import com.example.geolocationopenservicebroker.model.Plan;
import com.example.geolocationopenservicebroker.model.ServiceDefinition;
import com.example.geolocationopenservicebroker.model.Catalog;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MockServiceCatalog {

    @Bean
    public Catalog mockCatalog() {
        Plan geolocationBasicPlan = Plan.builder()
                .id("examplePlanId")
                .name("geolocation-basic-plan")
                .description("Geolocation Basic Plan")
                .maintenanceInfo(new MaintenanceInfo("0.0.1", "First version"))
                .bindable(true)
                .maximumPollingDuration(1000)
                .metadata("displayName", "geolocation-service-basic-plan")
                .metadata("longDescription", "Coordinates and geolocation provider service")
                .plan_updateable(true)
                .free(true)
                .build();

        ServiceDefinition serviceDefinition = new ServiceDefinition(
                "exampleServiceDefinitionId",
                "geolocation-service",
                "Geolocation Service",
                true,
                Arrays.asList("geolocation", "service"),
                Arrays.asList(geolocationBasicPlan)
        );

        return new Catalog(
                Arrays.asList(serviceDefinition)
        );
    }
}
