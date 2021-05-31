package com.example.geolocationopenservicebroker.api.dto.catalog;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


@Builder
@Data
public class MaintenanceInfoDto {
    @NonNull
    private final String version;
    private final String description;
}
