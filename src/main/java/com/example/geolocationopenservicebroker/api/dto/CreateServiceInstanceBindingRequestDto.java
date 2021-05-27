package com.example.geolocationopenservicebroker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CreateServiceInstanceBindingRequestDto {
    @JsonIgnore
    private transient String serviceInstanceId;
    @JsonIgnore
    private transient String bindingId;
    @NotEmpty
    @JsonProperty("service_id")
    private final String serviceDefinitionId;

    @NotEmpty
    @JsonProperty("plan_id")
    private final String planId;

    public CreateServiceInstanceBindingRequestDto(String serviceInstanceId, String bindingId, String serviceDefinitionId, String planId) {
        this.serviceInstanceId = serviceInstanceId;
        this.bindingId = bindingId;
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
    }

    public String getServiceInstanceId() {
        return this.serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getBindingId() {
        return this.bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public String getServiceDefinitionId() {
        return this.serviceDefinitionId;
    }

    public String getPlanId() {
        return this.planId;
    }
}
