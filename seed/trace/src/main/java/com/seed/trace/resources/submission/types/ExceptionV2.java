package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;

public final class ExceptionV2 {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private ExceptionV2(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static ExceptionV2 generic(ExceptionInfo value) {
        return new ExceptionV2(new GenericValue(value));
    }

    public static ExceptionV2 timeout() {
        return new ExceptionV2(new TimeoutValue());
    }

    public boolean isGeneric() {
        return value instanceof GenericValue;
    }

    public boolean isTimeout() {
        return value instanceof TimeoutValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<ExceptionInfo> getGeneric() {
        if (isGeneric()) {
            return Optional.of(((GenericValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Object> _getUnknown() {
        if (_isUnknown()) {
            return Optional.of(((_UnknownValue) value).value);
        }
        return Optional.empty();
    }

    @JsonValue
    private Value getValue() {
        return this.value;
    }

    public interface Visitor<T> {
        T visitGeneric(ExceptionInfo generic);

        T visitTimeout();

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(GenericValue.class), @JsonSubTypes.Type(TimeoutValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("generic")
    private static final class GenericValue implements Value {
        @JsonUnwrapped
        private ExceptionInfo value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GenericValue() {}

        private GenericValue(ExceptionInfo value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitGeneric(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof GenericValue && equalTo((GenericValue) other);
        }

        private boolean equalTo(GenericValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "ExceptionV2{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("timeout")
    private static final class TimeoutValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private TimeoutValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitTimeout();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof TimeoutValue;
        }

        @Override
        public String toString() {
            return "ExceptionV2{" + "}";
        }
    }

    private static final class _UnknownValue implements Value {
        private String type;

        @JsonValue
        private Object value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private _UnknownValue(@JsonProperty("value") Object value) {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor._visitUnknown(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
        }

        private boolean equalTo(_UnknownValue other) {
            return type.equals(other.type) && value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.type, this.value);
        }

        @Override
        public String toString() {
            return "ExceptionV2{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
