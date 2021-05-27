package com.example.geolocationopenservicebroker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteServiceInstanceBindingRequestDto {

    @JsonIgnore
    private final String serviceInstanceId;

    @JsonIgnore
    private final String bindingId;

    @JsonProperty("service_id")
    private final String serviceDefinitionId;

    @JsonProperty("plan_id")
    private final String planId;

    public DeleteServiceInstanceBindingRequestDto(String serviceInstanceId, String bindingId, String serviceDefinitionId, String planId) {
        this.serviceInstanceId = serviceInstanceId;
        this.bindingId = bindingId;
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
    }

    public String getServiceInstanceId() {
        return this.serviceInstanceId;
    }

    public String getBindingId() {
        return this.bindingId;
    }

    public String getServiceDefinitionId() { return serviceDefinitionId; }

    public String getPlanId() { return planId; }
}
