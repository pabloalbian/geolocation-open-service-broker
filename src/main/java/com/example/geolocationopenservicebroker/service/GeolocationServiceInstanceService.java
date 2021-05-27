package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceResponseDto;
import com.example.geolocationopenservicebroker.api.dto.DeleteServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import com.example.geolocationopenservicebroker.model.GeolocationServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class GeolocationServiceInstanceService {

    private final GeolocationService geolocationService;

    public GeolocationServiceInstanceService(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    public CreateServiceInstanceResponseDto createServiceInstance(CreateServiceInstanceRequestDto request) {
        Boolean serviceInstanceExists = geolocationService.serviceInstanceExistsById(request.getServiceInstanceId());

        if (serviceInstanceExists) {
            GeolocationServiceInstance existingServiceBinding = geolocationService.getServiceInstance(
                    request.getServiceInstanceId()
            );
            return new CreateServiceInstanceResponseDto(
                    existingServiceBinding.getDashboardUrl(),
                    true
            );
        } else {
            GeolocationServiceInstance createdServiceInstance = geolocationService.createServiceInstance(
                    request.getServiceInstanceId(), request.getServiceDefinitionId(), request.getPlanId());

            return new CreateServiceInstanceResponseDto(
                    createdServiceInstance.getDashboardUrl(),
                    false
            );
        }
    }

    public String deleteServiceInstance(DeleteServiceInstanceRequestDto deleteServiceInstanceRequest) {
        Boolean serviceInstanceExists = geolocationService.serviceInstanceExistsById(
                deleteServiceInstanceRequest.getServiceInstanceId());

        if (serviceInstanceExists) {
            return geolocationService.deleteServiceInstanceById(deleteServiceInstanceRequest.getServiceInstanceId());
        } else {
            return null;
        }
    }
}
