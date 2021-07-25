package com.example.geolocationopenservicebroker.geolocation;

import com.example.geolocationopenservicebroker.model.GeolocationServiceBinding;
import com.example.geolocationopenservicebroker.model.GeolocationServiceInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GeolocationService {

    private Map<String, GeolocationServiceInstance> geolocationServiceInstances = new HashMap<>() {{
        put(
                "526387f9-bea6-4900-864a-7a82145d9082",
                GeolocationServiceInstance.builder()
                        .instanceId("526387f9-bea6-4900-864a-7a82145d9082")
                        .serviceDefinitionId("6ca20b8a-a182-48a6-ab8e-bc462a2af1d1")
                        .planId("f5e235c5-aaed-44e1-aaea-5c7bac221294")
                        .dashboardUrl("mockDashboardUrl.com/")
                        .build()
        );
    }};

    private Map<String, GeolocationServiceBinding> geolocationServiceBindings = new HashMap<>();

    public static final String URI_KEY = "uri";
    private final String geolocationDashboardBaseURL;
    private final String geolocationSystemBaseURL;

    public GeolocationService(
            @Value("${geolocation.dashboard.base-url}") String geolocationDashboardBaseURL,
            @Value("${geolocation.base-url}") String geolocationBaseURL) {
        this.geolocationDashboardBaseURL = geolocationDashboardBaseURL;
        this.geolocationSystemBaseURL = geolocationBaseURL;
    }

    public Boolean serviceInstanceExistsById(String instanceId) {
        return geolocationServiceInstances.containsKey(instanceId);
    }

    public GeolocationServiceInstance getServiceInstance(String instanceId) {
        if (geolocationServiceInstances.containsKey(instanceId)) {
            return geolocationServiceInstances.get(instanceId);
        } else {
            throw new GeolocationOpenServiceBrokerException("Service instance does not exist");
        }
    }

    public GeolocationServiceInstance createServiceInstance(String instanceId, String serviceDefinitionId, String planId) {
        GeolocationServiceInstance geolocationServiceInstance = GeolocationServiceInstance.builder()
                .instanceId(instanceId)
                .serviceDefinitionId(serviceDefinitionId)
                .planId(planId)
                .dashboardUrl(geolocationDashboardBaseURL + instanceId)
                .build();


        geolocationServiceInstances.put(instanceId, geolocationServiceInstance);
        return geolocationServiceInstance;
    }

    public String deleteServiceInstanceById(String instanceId) {
        geolocationServiceInstances.remove(instanceId);
        geolocationServiceBindings.remove(instanceId);
        return instanceId;
    }

    public Boolean serviceBindingExistsById(String instanceId, String bindingId) {
        return geolocationServiceBindings.containsKey(instanceId) &&
                geolocationServiceBindings.get(instanceId).getBindingId().equalsIgnoreCase(bindingId);
    }

    public GeolocationServiceBinding getServiceBinding(String instanceId) {
        return geolocationServiceBindings.get(instanceId);
    }

    public GeolocationServiceBinding createServiceBinding(String instanceId, String bindingId) {
        Boolean serviceInstanceExists = this.serviceInstanceExistsById(instanceId);
        if (serviceInstanceExists) {
            GeolocationServiceBinding geolocationServiceBinding = GeolocationServiceBinding.builder()
                    .bindingId(bindingId)
                    .credentials(buildCredentials(instanceId, bindingId))
                    .build();
            geolocationServiceBindings.put(instanceId, geolocationServiceBinding);
            return geolocationServiceBinding;
        } else {
            throw new GeolocationOpenServiceBrokerException("Service instance does not exist");
        }
    }

    private Map<String, Object> buildCredentials(String instanceId, String bindingId) {
        Map<String, Object> credentials = new HashMap<>();
        credentials.put(URI_KEY, geolocationSystemBaseURL + instanceId);
        credentials.put("username", bindingId);
        credentials.put("password", UUID.randomUUID().toString());
        return credentials;
    }

    public void deleteServiceBinding(String instanceId) {
        geolocationServiceBindings.remove(instanceId);
    }
}
