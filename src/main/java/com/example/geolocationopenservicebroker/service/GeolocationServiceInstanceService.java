package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
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
    @Override
    public Mono<CreateServiceInstanceResponse> createServiceInstance(CreateServiceInstanceRequest request) {
        return Mono.just(request.getServiceInstanceId())
                .flatMap(instanceId -> Mono.just(CreateServiceInstanceResponse.builder())
                        .flatMap(responseBuilder -> geolocationService.serviceInstanceExistsById(instanceId)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return geolocationService.getServiceInstance(instanceId)
                                                .flatMap(geolocationServiceInstance -> Mono.just(responseBuilder
                                                        .instanceExisted(true)
                                                        .dashboardUrl(geolocationServiceInstance.getDashboardUrl())
                                                        .build()));
                                    } else {
                                        return geolocationService.createServiceInstance(
                                                instanceId, request.getServiceDefinitionId(), request.getPlanId())
                                                .flatMap(geolocationServiceInstance -> Mono.just(responseBuilder
                                                        .instanceExisted(false)
                                                        .dashboardUrl(geolocationServiceInstance.getDashboardUrl())
                                                        .build()));
                                    }
                                })));
    }

    @Override
    public Mono<DeleteServiceInstanceResponse> deleteServiceInstance(DeleteServiceInstanceRequest deleteServiceInstanceRequest) {
        return Mono.just(deleteServiceInstanceRequest.getServiceInstanceId())
                .flatMap(instanceId -> geolocationService.serviceInstanceExistsById(instanceId)
                        .flatMap(exists -> {
                            if (exists) {
                                return geolocationService.deleteServiceInstanceById(instanceId)
                                        .thenReturn(DeleteServiceInstanceResponse.builder().build());
                            } else {
                                return Mono.error(new ServiceInstanceDoesNotExistException(instanceId));
                            }
                        }));
    }
}
