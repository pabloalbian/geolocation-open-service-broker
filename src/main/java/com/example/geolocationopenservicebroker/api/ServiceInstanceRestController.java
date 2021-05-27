package com.example.geolocationopenservicebroker.api;

import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.api.dto.CreateServiceInstanceResponseDto;
import com.example.geolocationopenservicebroker.api.dto.DeleteServiceInstanceRequestDto;
import com.example.geolocationopenservicebroker.service.GeolocationServiceInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ServiceInstanceRestController {

    private final GeolocationServiceInstanceService serviceInstanceService;

    public ServiceInstanceRestController(GeolocationServiceInstanceService serviceInstanceService) {
        this.serviceInstanceService = serviceInstanceService;
    }

    @PutMapping(
            value = "/v2/service_instances/{serviceInstanceId}",
            consumes = "application/json",
            produces="application/json"
    )
    public ResponseEntity<CreateServiceInstanceResponseDto> createServiceInstance(
            @PathVariable("serviceInstanceId") String serviceInstanceId,
            @Valid @RequestBody CreateServiceInstanceRequestDto createServiceInstanceRequest
    ) {
        createServiceInstanceRequest.setServiceInstanceId(serviceInstanceId);
        CreateServiceInstanceResponseDto response = serviceInstanceService.createServiceInstance(createServiceInstanceRequest);

        if (response.isInstanceExisted()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/v2/service_instances/{serviceInstanceId}")
    public ResponseEntity<Void> deleteServiceInstance(
            @PathVariable("serviceInstanceId") String serviceInstanceId,
            @RequestParam("service_id") String serviceDefinitionId,
            @RequestParam("plan_id") String planId
    ) {
        String deletedServiceInstanceId = serviceInstanceService.deleteServiceInstance(
                new DeleteServiceInstanceRequestDto(
                        serviceInstanceId,
                        serviceDefinitionId,
                        planId
                )
        );

        if (deletedServiceInstanceId == null) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }

        return ResponseEntity.ok().build();
    }
}
