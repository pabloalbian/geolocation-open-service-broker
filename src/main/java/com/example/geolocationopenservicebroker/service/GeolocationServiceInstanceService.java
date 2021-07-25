package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.api.dto.serviceinstance.CreateServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.api.dto.serviceinstance.CreateServiceInstanceResponseDto;
import com.example.geolocationopenservicebroker.api.dto.serviceinstance.DeleteServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import com.example.geolocationopenservicebroker.model.GeolocationServiceInstance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeolocationServiceInstanceService {

    private final GeolocationService geolocationService;

    public CreateServiceInstanceResponseDto createServiceInstance(CreateServiceInstanceRequestDto request) {
        Boolean serviceInstanceExists = geolocationService.serviceInstanceExistsById(request.getServiceInstanceId());

        if (serviceInstanceExists) {
            GeolocationServiceInstance existingServiceBinding = geolocationService.getServiceInstance(
                    request.getServiceInstanceId()
            );

            return CreateServiceInstanceResponseDto.builder()
                    .dashboardUrl(existingServiceBinding.getDashboardUrl())
                    .instanceExisted(true)
                    .build();
        } else {
            GeolocationServiceInstance createdServiceInstance = geolocationService.createServiceInstance(
                    request.getServiceInstanceId(), request.getServiceDefinitionId(), request.getPlanId());

            return CreateServiceInstanceResponseDto.builder()
                    .dashboardUrl(createdServiceInstance.getDashboardUrl())
                    .instanceExisted(false)
                    .build();
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
