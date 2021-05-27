package com.example.geolocationopenservicebroker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class CreateServiceInstanceRequestDto {

    @NotEmpty
    @Pattern(regexp = "^((?!(Invalid)).)*$")
    @JsonProperty("service_id")
    private final String serviceDefinitionId;

    @NotEmpty
    @Pattern(regexp = "^((?!(Invalid)).)*$")
    @JsonProperty("plan_id")
    private final String planId;

    @JsonIgnore
    private transient String serviceInstanceId;

    public CreateServiceInstanceRequestDto(String serviceDefinitionId, String planId) {
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
    }

    public String getServiceInstanceId() {
        return this.serviceInstanceId;
    }

    public void setServiceInstanceId(final String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

    public String getPlanId() {
        return planId;
    }
}
