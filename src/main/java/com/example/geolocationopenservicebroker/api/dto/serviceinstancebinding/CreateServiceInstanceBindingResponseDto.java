package com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class CreateServiceInstanceBindingResponseDto {

    @JsonProperty("credentials")
    private final Map<String, Object> credentials;

    @JsonIgnore
    private final boolean bindingExisted;
}
