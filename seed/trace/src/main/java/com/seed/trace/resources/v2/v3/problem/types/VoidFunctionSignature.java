package com.seed.trace.resources.v2.v3.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = VoidFunctionSignature.Builder.class)
public final class VoidFunctionSignature {
    private final List<Parameter> parameters;

    private VoidFunctionSignature(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @JsonProperty("parameters")
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof VoidFunctionSignature && equalTo((VoidFunctionSignature) other);
    }

    private boolean equalTo(VoidFunctionSignature other) {
        return parameters.equals(other.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parameters);
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
        private List<Parameter> parameters = new ArrayList<>();

        private Builder() {}

        public Builder from(VoidFunctionSignature other) {
            parameters(other.getParameters());
            return this;
        }

        @JsonSetter(value = "parameters", nulls = Nulls.SKIP)
        public Builder parameters(List<Parameter> parameters) {
            this.parameters.clear();
            this.parameters.addAll(parameters);
            return this;
        }

        public Builder addParameters(Parameter parameters) {
            this.parameters.add(parameters);
            return this;
        }

        public Builder addAllParameters(List<Parameter> parameters) {
            this.parameters.addAll(parameters);
            return this;
        }

        public VoidFunctionSignature build() {
            return new VoidFunctionSignature(parameters);
        }
    }
}
