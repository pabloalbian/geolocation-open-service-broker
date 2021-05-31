package com.example.geolocationopenservicebroker.api.dto.catalog;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Builder
@Data
public class PlanDto {
    @NonNull
    private final String id;
    @NonNull
    private final String name;
    @NonNull
    private final String description;
    private final Map<String, Object> metadata;
    private final Boolean free;
    private final Boolean bindable;
    private final Boolean plan_updateable;
    private final Integer maximum_polling_duration;
    private final MaintenanceInfoDto maintenance_info;
}
