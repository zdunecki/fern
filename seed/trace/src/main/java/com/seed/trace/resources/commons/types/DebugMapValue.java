package com.seed.trace.resources.commons.types;

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
@JsonDeserialize(builder = DebugMapValue.Builder.class)
public final class DebugMapValue {
    private final List<DebugKeyValuePairs> keyValuePairs;

    private DebugMapValue(List<DebugKeyValuePairs> keyValuePairs) {
        this.keyValuePairs = keyValuePairs;
    }

    @JsonProperty("keyValuePairs")
    public List<DebugKeyValuePairs> getKeyValuePairs() {
        return keyValuePairs;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof DebugMapValue && equalTo((DebugMapValue) other);
    }

    private boolean equalTo(DebugMapValue other) {
        return keyValuePairs.equals(other.keyValuePairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.keyValuePairs);
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
        private List<DebugKeyValuePairs> keyValuePairs = new ArrayList<>();

        private Builder() {}

        public Builder from(DebugMapValue other) {
            keyValuePairs(other.getKeyValuePairs());
            return this;
        }

        @JsonSetter(value = "keyValuePairs", nulls = Nulls.SKIP)
        public Builder keyValuePairs(List<DebugKeyValuePairs> keyValuePairs) {
            this.keyValuePairs.clear();
            this.keyValuePairs.addAll(keyValuePairs);
            return this;
        }

        public Builder addKeyValuePairs(DebugKeyValuePairs keyValuePairs) {
            this.keyValuePairs.add(keyValuePairs);
            return this;
        }

        public Builder addAllKeyValuePairs(List<DebugKeyValuePairs> keyValuePairs) {
            this.keyValuePairs.addAll(keyValuePairs);
            return this;
        }

        public DebugMapValue build() {
            return new DebugMapValue(keyValuePairs);
        }
    }
}
