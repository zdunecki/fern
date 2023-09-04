package com.seed.trace.resources.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.VariableType;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = VariableTypeAndName.Builder.class)
public final class VariableTypeAndName {
    private final VariableType variableType;

    private final String name;

    private VariableTypeAndName(VariableType variableType, String name) {
        this.variableType = variableType;
        this.name = name;
    }

    @JsonProperty("variableType")
    public VariableType getVariableType() {
        return variableType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof VariableTypeAndName && equalTo((VariableTypeAndName) other);
    }

    private boolean equalTo(VariableTypeAndName other) {
        return variableType.equals(other.variableType) && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variableType, this.name);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static VariableTypeStage builder() {
        return new Builder();
    }

    public interface VariableTypeStage {
        NameStage variableType(VariableType variableType);

        Builder from(VariableTypeAndName other);
    }

    public interface NameStage {
        _FinalStage name(String name);
    }

    public interface _FinalStage {
        VariableTypeAndName build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements VariableTypeStage, NameStage, _FinalStage {
        private VariableType variableType;

        private String name;

        private Builder() {}

        @Override
        public Builder from(VariableTypeAndName other) {
            variableType(other.getVariableType());
            name(other.getName());
            return this;
        }

        @Override
        @JsonSetter("variableType")
        public NameStage variableType(VariableType variableType) {
            this.variableType = variableType;
            return this;
        }

        @Override
        @JsonSetter("name")
        public _FinalStage name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public VariableTypeAndName build() {
            return new VariableTypeAndName(variableType, name);
        }
    }
}
