package com.example.geolocationopenservicebroker.model;

import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Plan() {
        this((String)null, (String)null, (String)null, new HashMap(), (Boolean)null, (Boolean)null, (Boolean)null, (Integer)null, (MaintenanceInfo)null);
    }

    public Plan(String id, String name, String description, Map<String, Object> metadata, Boolean free, Boolean bindable, Boolean plan_updatable, Integer maximum_polling_duration, MaintenanceInfo maintenance_info) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.metadata = metadata;
        this.free = free;
        this.bindable = bindable;
        this.plan_updatable = plan_updatable;
        this.maximum_polling_duration = maximum_polling_duration;
        this.maintenance_info = maintenance_info;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public Boolean isFree() {
        return this.free;
    }

    public Boolean isBindable() {
        return this.bindable;
    }

    public Boolean isplan_updateable() {
        return this.plan_updatable;
    }

    public Integer getMaximum_polling_duration() {
        return this.maximum_polling_duration;
    }

    public MaintenanceInfo getMaintenance_info() {
        return this.maintenance_info;
    }

    public static PlanBuilder builder() {
        return new PlanBuilder();
    }

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

    public static final class PlanBuilder {
        private String id;
        private String name;
        private String description;
        private Map<String, Object> metadata;
        private Boolean free;
        private Boolean bindable;
        private Boolean plan_updeatable;
        private Integer maximumPollingDuration;
        private MaintenanceInfo maintenanceInfo;

        private PlanBuilder() {
            this.free = true;
        }

        public PlanBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PlanBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlanBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PlanBuilder metadata(Map<String, Object> metadata) {
            if (CollectionUtils.isEmpty(metadata)) {
                return this;
            } else {
                if (this.metadata == null) {
                    this.metadata = new HashMap();
                }

                this.metadata.putAll(metadata);
                return this;
            }
        }

        public PlanBuilder metadata(String key, Object value) {
            if (key != null && value != null) {
                if (this.metadata == null) {
                    this.metadata = new HashMap();
                }

                this.metadata.put(key, value);
                return this;
            } else {
                return this;
            }
        }

        public PlanBuilder free(Boolean free) {
            this.free = free;
            return this;
        }

        public PlanBuilder bindable(Boolean bindable) {
            this.bindable = bindable;
            return this;
        }

        public PlanBuilder plan_updateable(Boolean plan_updeatable) {
            this.plan_updeatable = plan_updeatable;
            return this;
        }

        public PlanBuilder maximumPollingDuration(Integer maximumPollingDuration) {
            this.maximumPollingDuration = maximumPollingDuration;
            return this;
        }

        public PlanBuilder maintenanceInfo(MaintenanceInfo maintenanceInfo) {
            this.maintenanceInfo = maintenanceInfo;
            return this;
        }

        public Plan build() {
            return new Plan(this.id, this.name, this.description, this.metadata, this.free, this.bindable, this.plan_updeatable, this.maximumPollingDuration, this.maintenanceInfo);
        }
    }
}
