package com.example.geolocationopenservicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceInfo {

    @NotNull
    private final String version;

    private final String description;
}
