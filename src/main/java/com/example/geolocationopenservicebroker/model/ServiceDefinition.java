package com.example.geolocationopenservicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.*;

@Builder
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(Include.NON_NULL)
public class ServiceDefinition {
    @NotEmpty
    private final String id;
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String description;
    private final boolean bindable;
    @NotEmpty
    private final List<Plan> plans;
    private final List<String> tags;

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ServiceDefinition)) {
            return false;
        } else {
            ServiceDefinition that = (ServiceDefinition)o;
            return this.bindable == that.bindable && Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name) && Objects.equals(this.description, that.description) && Objects.equals(this.plans, that.plans) && Objects.equals(this.tags, that.tags);
        }
    }

    public final int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.bindable, this.plans, this.tags);
    }

    public String toString() {
        return "ServiceDefinition{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", bindable=" + this.bindable + ", plans=" + this.plans + ", tags=" + this.tags + '}';
    }
}
