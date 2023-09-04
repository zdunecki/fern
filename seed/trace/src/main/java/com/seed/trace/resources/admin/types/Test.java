package com.seed.trace.resources.admin.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;

public final class Test {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private Test(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static Test and(boolean value) {
        return new Test(new AndValue(value));
    }

    public static Test or(boolean value) {
        return new Test(new OrValue(value));
    }

    public boolean isAnd() {
        return value instanceof AndValue;
    }

    public boolean isOr() {
        return value instanceof OrValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<Boolean> getAnd() {
        if (isAnd()) {
            return Optional.of(((AndValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Boolean> getOr() {
        if (isOr()) {
            return Optional.of(((OrValue) value).value);
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
        T visitAnd(boolean and);

        T visitOr(boolean or);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(AndValue.class), @JsonSubTypes.Type(OrValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("and")
    private static final class AndValue implements Value {
        @JsonProperty("value")
        private boolean value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private AndValue(@JsonProperty("value") boolean value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitAnd(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof AndValue && equalTo((AndValue) other);
        }

        private boolean equalTo(AndValue other) {
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "Test{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("or")
    private static final class OrValue implements Value {
        @JsonProperty("value")
        private boolean value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private OrValue(@JsonProperty("value") boolean value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitOr(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof OrValue && equalTo((OrValue) other);
        }

        private boolean equalTo(OrValue other) {
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "Test{" + "value: " + value + "}";
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
            return "Test{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
