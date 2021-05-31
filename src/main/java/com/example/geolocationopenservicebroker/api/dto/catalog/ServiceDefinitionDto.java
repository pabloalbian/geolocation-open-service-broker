package com.example.geolocationopenservicebroker.api.dto.catalog;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Builder
@Data
public class ServiceDefinitionDto {
    @NonNull
    private final String id;
    @NonNull
    private final String name;
    @NonNull
    private final String description;
    private final boolean bindable;
    @NonNull
    private final List<PlanDto> plans;
    private final List<String> tags;
}
