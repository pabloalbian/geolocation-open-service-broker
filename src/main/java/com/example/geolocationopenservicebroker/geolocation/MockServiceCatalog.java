package com.example.geolocationopenservicebroker.geolocation;

import com.example.geolocationopenservicebroker.model.MaintenanceInfo;
import com.example.geolocationopenservicebroker.model.Plan;
import com.example.geolocationopenservicebroker.model.ServiceDefinition;
import com.example.geolocationopenservicebroker.model.Catalog;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class MockServiceCatalog {

    @Bean
    public Catalog mockCatalog() {
        Plan geolocationBasicPlan = Plan.builder()
                .id("examplePlanId")
                .name("geolocation-basic-plan")
                .description("Geolocation Basic Plan")
                .maintenance_info(MaintenanceInfo.builder().version("0.0.1").description("First version").build())
                .bindable(true)
                .maximum_polling_duration(1000)
                .metadata(Collections.singletonMap("displayName", "geolocation-service-basic-plan"))
                .metadata(Collections.singletonMap("longDescription", "Coordinates and geolocation provider service"))
                .plan_updatable(true)
                .free(true)
                .build();

        ServiceDefinition serviceDefinition = ServiceDefinition.builder()
                .id("exampleServiceDefinitionId")
                .name("geolocation-service")
                .description("Geolocation Service")
                .bindable(true)
                .tags(Arrays.asList("geolocation", "service"))
                .plans(Collections.singletonList(geolocationBasicPlan))
                .build();

        return Catalog.builder().serviceDefinitions(Collections.singletonList(serviceDefinition)).build();
    }
}
