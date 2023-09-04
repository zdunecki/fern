package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.DebugVariableValue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = Scope.Builder.class)
public final class Scope {
    private final Map<String, DebugVariableValue> variables;

    private Scope(Map<String, DebugVariableValue> variables) {
        this.variables = variables;
    }

    @JsonProperty("variables")
    public Map<String, DebugVariableValue> getVariables() {
        return variables;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Scope && equalTo((Scope) other);
    }

    private boolean equalTo(Scope other) {
        return variables.equals(other.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variables);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Map<String, DebugVariableValue> variables = new LinkedHashMap<>();

        private Builder() {}

        public Builder from(Scope other) {
            variables(other.getVariables());
            return this;
        }

        @JsonSetter(value = "variables", nulls = Nulls.SKIP)
        public Builder variables(Map<String, DebugVariableValue> variables) {
            this.variables.clear();
            this.variables.putAll(variables);
            return this;
        }

        public Builder putAllVariables(Map<String, DebugVariableValue> variables) {
            this.variables.putAll(variables);
            return this;
        }

        public Builder variables(String key, DebugVariableValue value) {
            this.variables.put(key, value);
            return this;
        }

        public Scope build() {
            return new Scope(variables);
        }
    }
}
