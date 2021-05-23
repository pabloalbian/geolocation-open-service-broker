package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.model.binding.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GeolocationServiceInstanceBindingService implements ServiceInstanceBindingService {

    private final GeolocationService geolocationService;

    public GeolocationServiceInstanceBindingService(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @Override
    public Mono<CreateServiceInstanceBindingResponse> createServiceInstanceBinding(
            CreateServiceInstanceBindingRequest request) {
        return Mono.just(CreateServiceInstanceAppBindingResponse.builder())
                .flatMap(responseBuilder -> geolocationService.serviceBindingExistsById(
                        request.getServiceInstanceId(), request.getBindingId())
                        .flatMap(exists -> {
                            if (exists) {
                                return geolocationService.getServiceBinding(
                                        request.getServiceInstanceId(), request.getBindingId())
                                        .flatMap(serviceBinding -> Mono.just(responseBuilder
                                                .bindingExisted(true)
                                                .credentials(serviceBinding.getCredentials())
                                                .build()));
                            } else {
                                return geolocationService.createServiceBinding(
                                        request.getServiceInstanceId(), request.getBindingId())
                                        .switchIfEmpty(Mono.error(
                                                new ServiceInstanceDoesNotExistException(
                                                        request.getServiceInstanceId())))
                                        .flatMap(geolocationServiceBinding -> Mono.just(responseBuilder
                                                .bindingExisted(false)
                                                .credentials(geolocationServiceBinding.getCredentials())
                                                .build()));
                            }
                        }));
    }

    @Override
    public Mono<DeleteServiceInstanceBindingResponse> deleteServiceInstanceBinding(
            DeleteServiceInstanceBindingRequest request) {
        return geolocationService.serviceBindingExistsById(request.getServiceInstanceId(), request.getBindingId())
                .flatMap(exists -> {
                    if (exists) {
                        return geolocationService.deleteServiceBinding(request.getServiceInstanceId())
                                .thenReturn(DeleteServiceInstanceBindingResponse.builder().build());
                    } else {
                        return Mono.error(new ServiceInstanceBindingDoesNotExistException(request.getBindingId()));
                    }
                });
    }
}
