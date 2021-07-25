package com.example.geolocationopenservicebroker.api.dto.serviceinstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteServiceInstanceRequestDto {
    @JsonIgnore
    private final transient String serviceInstanceId;
    @JsonProperty("service_id")
    private final transient String serviceDefinitionId;
    @JsonProperty("plan_id")
    private final transient String planId;
}
