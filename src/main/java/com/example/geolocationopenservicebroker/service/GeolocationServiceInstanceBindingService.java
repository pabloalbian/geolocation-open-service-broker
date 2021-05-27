package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceBindingRequestDto;
import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceBindingResponseDto;
import com.example.geolocationopenservicebroker.api.dto.DeleteServiceInstanceBindingRequestDto;
import com.example.geolocationopenservicebroker.geolocation.GeolocationService;
import com.example.geolocationopenservicebroker.model.GeolocationServiceBinding;
import org.springframework.stereotype.Service;

@Service
public class GeolocationServiceInstanceBindingService {

    private final GeolocationService geolocationService;

    public GeolocationServiceInstanceBindingService(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    public CreateServiceInstanceBindingResponseDto createServiceInstanceBinding(
            CreateServiceInstanceBindingRequestDto request) {
        Boolean serviceBindingExists = geolocationService.serviceBindingExistsById(
                request.getServiceInstanceId(),
                request.getBindingId()
        );

        if (serviceBindingExists) {
            GeolocationServiceBinding existingBinding =
                    geolocationService.getServiceBinding(request.getServiceInstanceId());

            return new CreateServiceInstanceBindingResponseDto(
                    existingBinding.getCredentials(), true
            );
        } else {
            GeolocationServiceBinding geolocationServiceBinding = geolocationService.createServiceBinding(
                    request.getServiceInstanceId(), request.getBindingId());

            return new CreateServiceInstanceBindingResponseDto(
                    geolocationServiceBinding.getCredentials(), false
            );
        }
    }

    public void deleteServiceInstanceBinding(
            DeleteServiceInstanceBindingRequestDto request) {
        Boolean serviceBindingExists = geolocationService.serviceBindingExistsById(
                request.getServiceInstanceId(),
                request.getBindingId()
        );

        if (serviceBindingExists) {
            geolocationService.deleteServiceBinding(request.getServiceInstanceId());
        } else {
        }
    }
}
