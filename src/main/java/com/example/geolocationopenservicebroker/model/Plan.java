package com.example.geolocationopenservicebroker.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Data
public class Plan {
    @NotEmpty
    private final String id;
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String description;
    private final Map<String, Object> metadata;
    private final Boolean free;
    private final Boolean bindable;
    private final Boolean plan_updatable;
    private final Integer maximum_polling_duration;
    private final MaintenanceInfo maintenance_info;

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Plan)) {
            return false;
        } else {
            Plan plan = (Plan)o;
            return Objects.equals(this.id, plan.id) && Objects.equals(this.name, plan.name) && Objects.equals(this.description, plan.description) && Objects.equals(this.metadata, plan.metadata) && Objects.equals(this.free, plan.free) && Objects.equals(this.bindable, plan.bindable) && Objects.equals(this.plan_updatable, plan.plan_updatable) && Objects.equals(this.maximum_polling_duration, plan.maximum_polling_duration) && Objects.equals(this.maintenance_info, plan.maintenance_info);
        }
    }

    public final int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.metadata, this.free, this.bindable, this.plan_updatable, this.maximum_polling_duration, this.maintenance_info);
    }

    public String toString() {
        return "Plan{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", metadata=" + this.metadata + ", free=" + this.free + ", bindable=" + this.bindable + ", plan_updatable=" + this.plan_updatable + ", maximum_polling_duration=" + this.maximum_polling_duration + ", maintenance_info=" + this.maintenance_info + '}';
    }
}
