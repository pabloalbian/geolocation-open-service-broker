package com.example.geolocationopenservicebroker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.*;

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

    public ServiceDefinition() {
        this((String)null, (String)null, (String)null, false, new ArrayList(), new ArrayList());
    }

    public ServiceDefinition(String id, String name, String description, boolean bindable, List<String> tags, List<Plan> plans) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.bindable = bindable;
        this.plans = plans;
        this.tags = tags;
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

    public boolean isBindable() {
        return this.bindable;
    }

    public List<Plan> getPlans() {
        return this.plans;
    }

    public List<String> getTags() {
        return this.tags;
    }

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

    public static final class ServiceDefinitionBuilder {
        private String id;
        private String name;
        private String description;
        private boolean bindable;
        private Boolean plan_updeatable;
        private Boolean instancesRetrievable;
        private Boolean bindingsRetrievable;
        private Boolean allowContextUpdates;
        private final List<Plan> plans;
        private List<String> tags;
        private Map<String, Object> metadata;
        private List<String> requires;

        private ServiceDefinitionBuilder() {
            this.plans = new ArrayList();
        }

        public ServiceDefinitionBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ServiceDefinitionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceDefinitionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ServiceDefinitionBuilder bindable(boolean bindable) {
            this.bindable = bindable;
            return this;
        }

        public ServiceDefinitionBuilder plan_updateable(Boolean plan_updateable) {
            this.plan_updeatable = plan_updateable;
            return this;
        }

        public ServiceDefinitionBuilder instancesRetrievable(Boolean instancesRetrievable) {
            this.instancesRetrievable = instancesRetrievable;
            return this;
        }

        public ServiceDefinitionBuilder bindingsRetrievable(Boolean bindingsRetrievable) {
            this.bindingsRetrievable = bindingsRetrievable;
            return this;
        }

        public ServiceDefinitionBuilder allowContextUpdates(Boolean allowContextUpdates) {
            this.allowContextUpdates = allowContextUpdates;
            return this;
        }

        public ServiceDefinitionBuilder plans(Plan... plans) {
            Collections.addAll(this.plans, plans);
            return this;
        }

        public ServiceDefinitionBuilder plans(List<Plan> plans) {
            this.plans.addAll(plans);
            return this;
        }

        public ServiceDefinitionBuilder tags(String... tags) {
            if (this.tags == null) {
                this.tags = new ArrayList();
            }

            Collections.addAll(this.tags, tags);
            return this;
        }

        public ServiceDefinitionBuilder tags(List<String> tags) {
            if (this.tags == null) {
                this.tags = new ArrayList();
            }

            this.tags.addAll(tags);
            return this;
        }

        public ServiceDefinitionBuilder metadata(Map<String, Object> metadata) {
            if (this.metadata == null) {
                this.metadata = new HashMap();
            }

            if (!CollectionUtils.isEmpty(metadata)) {
                this.metadata.putAll(metadata);
            }

            return this;
        }

        public ServiceDefinitionBuilder metadata(String key, Object value) {
            if (this.metadata == null) {
                this.metadata = new HashMap();
            }

            this.metadata.put(key, value);
            return this;
        }

        public ServiceDefinitionBuilder requires(String... requires) {
            if (this.requires == null) {
                this.requires = new ArrayList();
            }

            Collections.addAll(this.requires, requires);
            return this;
        }

        public ServiceDefinitionBuilder requires(List<String> requires) {
            if (this.requires == null) {
                this.requires = new ArrayList();
            }

            this.requires.addAll(requires);
            return this;
        }
    }
}
