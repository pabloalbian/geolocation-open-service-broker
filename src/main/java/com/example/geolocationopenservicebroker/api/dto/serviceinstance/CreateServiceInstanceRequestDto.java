package com.example.geolocationopenservicebroker.api.dto.serviceinstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Data
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
}
