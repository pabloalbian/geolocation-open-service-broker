package com.example.geolocationopenservicebroker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteServiceInstanceRequestDto {
    @JsonIgnore
    private final transient String serviceInstanceId;
    @JsonProperty("service_id")
    private final transient String serviceDefinitionId;
    @JsonProperty("plan_id")
    private final transient String planId;

    public DeleteServiceInstanceRequestDto(String serviceInstanceId, String serviceDefinitionId, String planId) {
        this.serviceInstanceId = serviceInstanceId;
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
    }

    public String getServiceInstanceId() {
        return this.serviceInstanceId;
    }

    public String getServiceDefinitionId() {
        return this.serviceDefinitionId;
    }

    public String getPlanId() {
        return this.planId;
    }
}
