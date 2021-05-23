package com.example.geolocationopenservicebroker.geolocation;

import java.util.Map;

public class GeolocationServiceBinding {

    private String bindingId;
    private Map<String, Object> credentials;

    public GeolocationServiceBinding(String bindingId, Map<String, Object> credentials) {
        this.bindingId = bindingId;
        this.credentials = credentials;
    }

    public String getBindingId() {
        return bindingId;
    }

    public Map<String, Object> getCredentials() {
        return credentials;
    }
}
