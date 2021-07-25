package com.example.geolocationopenservicebroker.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class GeolocationServiceBinding {

    private final String bindingId;
    private final Map<String, Object> credentials;
}
