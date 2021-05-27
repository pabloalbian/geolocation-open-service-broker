package com.example.geolocationopenservicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceInfo {

    @NotNull
    private final String version;

    private final String description;

    public MaintenanceInfo(String version, String description) {
        this.version = version;
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
}
