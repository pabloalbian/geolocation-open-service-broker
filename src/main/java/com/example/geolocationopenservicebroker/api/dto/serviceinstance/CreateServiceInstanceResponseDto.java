package com.example.geolocationopenservicebroker.api.dto.serviceinstance;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateServiceInstanceResponseDto {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("dashboard_url")
    private final String dashboardUrl;

    @JsonIgnore
    private final boolean instanceExisted;
}
