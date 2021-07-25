package com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteServiceInstanceBindingRequestDto {

    @JsonIgnore
    private final String serviceInstanceId;

    @JsonIgnore
    private final String bindingId;

    @JsonProperty("service_id")
    private final String serviceDefinitionId;

    @JsonProperty("plan_id")
    private final String planId;
}
