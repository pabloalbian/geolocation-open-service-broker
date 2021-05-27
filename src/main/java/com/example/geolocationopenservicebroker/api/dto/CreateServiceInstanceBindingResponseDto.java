package com.example.geolocationopenservicebroker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CreateServiceInstanceBindingResponseDto {

    @JsonProperty("credentials")
    private final Map<String, Object> credentials;

    @JsonIgnore
    private final boolean bindingExisted;

    public CreateServiceInstanceBindingResponseDto(Map<String, Object> credentials, boolean bindingExisted) {
        this.credentials = credentials;
        this.bindingExisted = bindingExisted;
    }

    public Map<String, Object> getCredentials() {
        return credentials;
    }

    public boolean isBindingExisted() {
        return bindingExisted;
    }
}
