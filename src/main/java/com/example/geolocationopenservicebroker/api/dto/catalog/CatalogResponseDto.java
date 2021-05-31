package com.example.geolocationopenservicebroker.api.dto.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class CatalogResponseDto {
    @JsonProperty("services")
    private final List<ServiceDefinitionDto> serviceDefinitions;
}
