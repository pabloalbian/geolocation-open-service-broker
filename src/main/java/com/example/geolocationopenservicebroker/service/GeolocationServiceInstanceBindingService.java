package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding.CreateServiceInstanceBindingRequestDto;
import com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding.CreateServiceInstanceBindingResponseDto;
import com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding.DeleteServiceInstanceBindingRequestDto;
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
            String serviceInstanceId, String bindingId) {
        Boolean serviceBindingExists = geolocationService.serviceBindingExistsById(
                serviceInstanceId,
                bindingId
        );

        if (serviceBindingExists) {
            GeolocationServiceBinding existingBinding =
                    geolocationService.getServiceBinding(serviceInstanceId);

            return CreateServiceInstanceBindingResponseDto.builder()
                    .credentials(existingBinding.getCredentials())
                    .bindingExisted(true)
                    .build();
        } else {
            GeolocationServiceBinding geolocationServiceBinding = geolocationService.createServiceBinding(
                    serviceInstanceId, bindingId);

            return CreateServiceInstanceBindingResponseDto.builder()
                    .credentials(geolocationServiceBinding.getCredentials())
                    .bindingExisted(false)
                    .build();
        }
    }

    public void deleteServiceInstanceBinding(String serviceInstanceId, String bindingId) {
        Boolean serviceBindingExists = geolocationService.serviceBindingExistsById(
                serviceInstanceId,
                bindingId
        );

        if (serviceBindingExists) {
            geolocationService.deleteServiceBinding(serviceInstanceId);
        }
    }
}
