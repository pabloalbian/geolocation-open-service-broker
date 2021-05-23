package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GeolocationServiceInstanceService implements ServiceInstanceService {

    private final GeolocationService geolocationService;

    public GeolocationServiceInstanceService(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    // Todo: Solve deserialization issue
    // Todo: Implement some logic in GeoLocationService
    @Override
    public Mono<CreateServiceInstanceResponse> createServiceInstance(CreateServiceInstanceRequest request) {
        return Mono.just(request.getServiceInstanceId())
                .flatMap(instanceId -> Mono.just(CreateServiceInstanceResponse.builder())
                        .flatMap(responseBuilder -> geolocationService.serviceInstanceExistsById(instanceId)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return geolocationService.getServiceInstance(instanceId)
                                                .flatMap(mailServiceInstance -> Mono.just(responseBuilder
                                                        .instanceExisted(true)
                                                        .dashboardUrl("ExampleUrlInstanceExisted.com")
                                                        .build()));
                                    } else {
                                        return geolocationService.createServiceInstance(
                                                instanceId, request.getServiceDefinitionId(), request.getPlanId())
                                                .flatMap(mailServiceInstance -> Mono.just(responseBuilder
                                                        .instanceExisted(false)
                                                        .dashboardUrl("ExampleUrlInstanceNotExisted.com")
                                                        .build()));
                                    }
                                })));
    }

    @Override
    public Mono<DeleteServiceInstanceResponse> deleteServiceInstance(DeleteServiceInstanceRequest deleteServiceInstanceRequest) {
        return null;
    }
}
