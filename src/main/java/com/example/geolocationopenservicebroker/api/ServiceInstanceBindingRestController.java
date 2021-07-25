package com.example.geolocationopenservicebroker.api;

import com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding.CreateServiceInstanceBindingRequestDto;
import com.example.geolocationopenservicebroker.api.dto.serviceinstancebinding.CreateServiceInstanceBindingResponseDto;
import com.example.geolocationopenservicebroker.service.GeolocationServiceInstanceBindingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class ServiceInstanceBindingRestController {

    private final GeolocationServiceInstanceBindingService serviceInstanceBindingService;

    public ServiceInstanceBindingRestController(GeolocationServiceInstanceBindingService serviceInstanceBindingService) {
        this.serviceInstanceBindingService = serviceInstanceBindingService;
    }

    @PutMapping(
            value = "/v2/service_instances/{serviceInstanceId}/service_bindings/{bindingId}",
            consumes = "application/json",
            produces="application/json"
    )
    public ResponseEntity<CreateServiceInstanceBindingResponseDto> createServiceInstanceBinding(
            @PathVariable("serviceInstanceId") String serviceInstanceId,
            @PathVariable("bindingId") String bindingId,
            @Valid @RequestBody CreateServiceInstanceBindingRequestDto createServiceInstanceBindingRequestDto
    ) {
        CreateServiceInstanceBindingResponseDto response = serviceInstanceBindingService
                .createServiceInstanceBinding(serviceInstanceId, bindingId);

        if (response.isBindingExisted()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/v2/service_instances/{serviceInstanceId}/service_bindings/{bindingId}")
    public ResponseEntity<Void> createServiceInstanceBinding(
            @PathVariable("serviceInstanceId") String serviceInstanceId,
            @PathVariable("bindingId") String bindingId,
            @RequestParam("service_id") String serviceDefinitionId,
            @RequestParam("plan_id") String planId
    ) {
        if (serviceDefinitionId.isBlank() || planId.isBlank()) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }

        serviceInstanceBindingService.deleteServiceInstanceBinding(serviceInstanceId, bindingId);

        return ResponseEntity.ok().build();
    }
}
