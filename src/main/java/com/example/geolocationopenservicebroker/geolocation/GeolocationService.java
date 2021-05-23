package com.example.geolocationopenservicebroker.geolocation;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GeolocationService {

    public Mono<Boolean> serviceInstanceExistsById(String instanceId) {
        return Mono.just(true);
    }

    public Mono<Object> getServiceInstance(String instanceId) {
        return Mono.empty();
    }

    public Mono<Object> createServiceInstance(String instanceId, String serviceDefinitionId, String planId) {
        return Mono.empty();
    }
}
