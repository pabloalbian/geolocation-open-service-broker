package com.example.geolocationopenservicebroker.geolocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GeolocationService {

    private Map<String, GeolocationServiceInstance> geolocationServices = new HashMap<>() {{
        put("526387f9-bea6-4900-864a-7a82145d9082", new GeolocationServiceInstance(
                "526387f9-bea6-4900-864a-7a82145d9082",
                "6ca20b8a-a182-48a6-ab8e-bc462a2af1d1",
                "f5e235c5-aaed-44e1-aaea-5c7bac221294",
                "mockDashboardUrl.com/"
        ));
    }};

    private Map<String, GeolocationServiceBinding> geolocationServiceBindings = new HashMap<>();

    public static final String URI_KEY = "uri";
    private final String geolocationDashboardBaseURL;
    private final String geolocationSystemBaseURL;

    public GeolocationService(@Value("${geolocation.dashboard.base-url}") String geolocationDashboardBaseURL,
                       @Value("${geolocation.base-url}") String geolocationBaseURL) {
        this.geolocationDashboardBaseURL = geolocationDashboardBaseURL;
        this.geolocationSystemBaseURL = geolocationBaseURL;
    }

    public Mono<Boolean> serviceInstanceExistsById(String instanceId) {
        return Mono.just(geolocationServices.containsKey(instanceId));
    }

    public Mono<GeolocationServiceInstance> getServiceInstance(String instanceId) {
        if (geolocationServices.containsKey(instanceId)) {
            return Mono.just(geolocationServices.get(instanceId));
        }
        return Mono.empty();
    }

    public Mono<GeolocationServiceInstance> createServiceInstance(String instanceId, String serviceDefinitionId, String planId) {
        GeolocationServiceInstance geolocationServiceInstance = new GeolocationServiceInstance(
                instanceId, serviceDefinitionId, planId, geolocationDashboardBaseURL + instanceId);
        geolocationServices.put(instanceId, geolocationServiceInstance);
        return Mono.just(geolocationServiceInstance);
    }

    public Mono<Void> deleteServiceInstanceById(String instanceId) {
        geolocationServices.remove(instanceId);
        geolocationServiceBindings.remove(instanceId);
        return Mono.empty();
    }

    public Mono<Boolean> serviceBindingExistsById(String instanceId, String bindingId) {
        return Mono.just(geolocationServiceBindings.containsKey(instanceId) &&
                geolocationServiceBindings.get(instanceId).getBindingId().equalsIgnoreCase(bindingId));
    }

    public Mono<GeolocationServiceBinding> getServiceBinding(String instanceId, String bindingId) {
        if (geolocationServiceBindings.containsKey(instanceId) &&
                geolocationServiceBindings.get(instanceId).getBindingId().equalsIgnoreCase(bindingId)) {
            return Mono.just(geolocationServiceBindings.get(instanceId));
        }
        return Mono.empty();
    }

    public Mono<GeolocationServiceBinding> createServiceBinding(String instanceId, String bindingId) {
        return this.serviceInstanceExistsById(instanceId)
                .flatMap(exists -> {
                    if (exists) {
                        GeolocationServiceBinding geolocationServiceBinding =
                                new GeolocationServiceBinding(bindingId, buildCredentials(instanceId, bindingId));
                        geolocationServiceBindings.put(instanceId, geolocationServiceBinding);
                        return Mono.just(geolocationServiceBinding);
                    } else {
                        return Mono.empty();
                    }
                });
    }

    private Map<String, Object> buildCredentials(String instanceId, String bindingId) {
        Map<String, Object> credentials = new HashMap<>();
        credentials.put(URI_KEY, geolocationSystemBaseURL + instanceId);
        credentials.put("username", bindingId);
        credentials.put("password", UUID.randomUUID().toString());
        return credentials;
    }

    public Mono<Void> deleteServiceBinding(String instanceId) {
        geolocationServiceBindings.remove(instanceId);
        return Mono.empty();
    }
}
