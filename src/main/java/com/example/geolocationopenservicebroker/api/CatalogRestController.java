package com.example.geolocationopenservicebroker.api;


import com.example.geolocationopenservicebroker.api.dto.catalog.CatalogResponseDto;
import com.example.geolocationopenservicebroker.api.dto.catalog.MaintenanceInfoDto;
import com.example.geolocationopenservicebroker.api.dto.catalog.PlanDto;
import com.example.geolocationopenservicebroker.api.dto.catalog.ServiceDefinitionDto;
import com.example.geolocationopenservicebroker.model.Catalog;
import com.example.geolocationopenservicebroker.model.Plan;
import com.example.geolocationopenservicebroker.model.ServiceDefinition;
import com.example.geolocationopenservicebroker.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CatalogRestController {

    private final CatalogService catalogService;

    CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/v2/catalog")
    public ResponseEntity<CatalogResponseDto> getCatalog() {
        Catalog catalog = catalogService.getCatalog();
        ServiceDefinition serviceDefinition = catalog.getServiceDefinitions().get(0);
        Plan plan = serviceDefinition.getPlans().get(0);

        // Todo: Implement mapping in independent methods
        CatalogResponseDto response = CatalogResponseDto.builder()
                .serviceDefinitions(
                        Arrays.asList(
                                ServiceDefinitionDto.builder()
                                        .id(serviceDefinition.getId())
                                        .description(serviceDefinition.getDescription())
                                        .name(serviceDefinition.getName())
                                        .plans(
                                                Arrays.asList(
                                                        PlanDto.builder()
                                                                .id(plan.getId())
                                                                .name(plan.getName())
                                                                .bindable(plan.isBindable())
                                                                .free(plan.isFree())
                                                                .description(plan.getDescription())
                                                                .metadata(plan.getMetadata())
                                                                .maintenance_info(
                                                                        MaintenanceInfoDto.builder()
                                                                                .description(plan.getMaintenance_info().getDescription())
                                                                                .version(plan.getMaintenance_info().getVersion())
                                                                                .build()
                                                                )
                                                                .maximum_polling_duration(plan.getMaximum_polling_duration())
                                                                .plan_updateable(plan.isplan_updateable())
                                                                .build()
                                                )
                                        )
                                        .bindable(serviceDefinition.isBindable())
                                        .tags(serviceDefinition.getTags())
                                        .build()
                        )
                ).build();

        return ResponseEntity.ok().body(response);
    }
}
