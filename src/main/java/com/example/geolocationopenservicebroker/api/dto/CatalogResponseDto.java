package com.example.geolocationopenservicebroker.api.dto;

import com.example.geolocationopenservicebroker.model.ServiceDefinition;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CatalogResponseDto {
    @NotEmpty
    @JsonProperty("services")
    private final List<ServiceDefinition> serviceDefinitions;

    public CatalogResponseDto(List<ServiceDefinition> serviceDefinitions) {
        this.serviceDefinitions = serviceDefinitions;
    }
}
