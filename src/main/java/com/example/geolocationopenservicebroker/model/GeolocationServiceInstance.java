package com.example.geolocationopenservicebroker.model;

public class GeolocationServiceInstance {
    private final String instanceId;
    private final String serviceDefinitionId;
    private final String planId;
    private final String dashboardUrl;

    public GeolocationServiceInstance(String instanceId, String serviceDefinitionId, String planId, String dashboardUrl) {
        this.instanceId = instanceId;
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
        this.dashboardUrl = dashboardUrl;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

    public String getPlanId() {
        return planId;
    }

    public String getDashboardUrl() {
        return dashboardUrl;
    }
}
