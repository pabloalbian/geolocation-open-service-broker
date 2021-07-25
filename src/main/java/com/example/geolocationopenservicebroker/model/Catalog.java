package com.example.geolocationopenservicebroker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class Catalog {
    @NotEmpty
    @JsonProperty("services")
    private final List<ServiceDefinition> serviceDefinitions;

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Catalog)) {
            return false;
        } else {
            Catalog catalog = (Catalog)o;
            return Objects.equals(this.serviceDefinitions, catalog.serviceDefinitions);
        }
    }

    public final int hashCode() {
        return Objects.hash(this.serviceDefinitions);
    }

    public String toString() {
        return "Catalog{serviceDefinitions=" + this.serviceDefinitions + '}';
    }
}
