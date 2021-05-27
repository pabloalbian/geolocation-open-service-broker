package com.example.geolocationopenservicebroker.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateServiceInstanceResponseDto {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("dashboard_url")
    private final String dashboardUrl;

    @JsonIgnore
    private final boolean instanceExisted;

    public CreateServiceInstanceResponseDto(String dashboardUrl, boolean instanceExisted) {
        this.dashboardUrl = dashboardUrl;
        this.instanceExisted = instanceExisted;
    }

    public String getDashboardUrl() {
        return dashboardUrl;
    }

    public boolean isInstanceExisted() {
        return instanceExisted;
    }
}
