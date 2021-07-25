package com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
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
}
