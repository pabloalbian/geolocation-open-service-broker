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

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CatalogRestController {

    private final CatalogService catalogService;

    CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/v2/catalog")
    public ResponseEntity<CatalogResponseDto> getCatalog() {
        Catalog catalog = catalogService.getMockServiceCatalog().mockCatalog();

        CatalogResponseDto response = CatalogResponseDto.builder()
                .serviceDefinitions(
                        mapServiceDefinitionsToServiceDefinitionDtos(catalog.getServiceDefinitions())
                )
                .build();

        return ResponseEntity.ok().body(response);
    }

    private List<ServiceDefinitionDto> mapServiceDefinitionsToServiceDefinitionDtos(
            List<ServiceDefinition> serviceDefinitions
    ) {
        return serviceDefinitions.stream().map(serviceDefinition ->
                        ServiceDefinitionDto.builder()
                                .id(serviceDefinition.getId())
                                .description(serviceDefinition.getDescription())
                                .name(serviceDefinition.getName())
                                .plans(
                                        mapPlansToPlanDtos(serviceDefinition.getPlans())
                                )
                                .bindable(serviceDefinition.isBindable())
                                .tags(serviceDefinition.getTags())
                                .build()
                ).collect(Collectors.toList());
    }

    private List<PlanDto> mapPlansToPlanDtos(List<Plan> plans) {
        return plans.stream().map(plan ->
            PlanDto.builder()
                    .id(plan.getId())
                    .name(plan.getName())
                    .bindable(plan.getBindable())
                    .free(plan.getFree())
                    .description(plan.getDescription())
                    .metadata(plan.getMetadata())
                    .maintenance_info(
                            MaintenanceInfoDto.builder()
                                    .description(plan.getMaintenance_info().getDescription())
                                    .version(plan.getMaintenance_info().getVersion())
                                    .build()
                    )
                    .maximum_polling_duration(plan.getMaximum_polling_duration())
                    .plan_updateable(plan.getPlan_updatable())
                    .build()
        ).collect(Collectors.toList());
    }
}
