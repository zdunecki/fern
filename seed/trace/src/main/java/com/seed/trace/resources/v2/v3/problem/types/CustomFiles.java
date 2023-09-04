package com.seed.trace.resources.v2.v3.problem.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.seed.trace.resources.commons.types.Language;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class CustomFiles {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private CustomFiles(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static CustomFiles basic(BasicCustomFiles value) {
        return new CustomFiles(new BasicValue(value));
    }

    public static CustomFiles custom(Map<Language, Files> value) {
        return new CustomFiles(new CustomValue(value));
    }

    public boolean isBasic() {
        return value instanceof BasicValue;
    }

    public boolean isCustom() {
        return value instanceof CustomValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<BasicCustomFiles> getBasic() {
        if (isBasic()) {
            return Optional.of(((BasicValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Map<Language, Files>> getCustom() {
        if (isCustom()) {
            return Optional.of(((CustomValue) value).value);
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
        T visitBasic(BasicCustomFiles basic);

        T visitCustom(Map<Language, Files> custom);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(BasicValue.class), @JsonSubTypes.Type(CustomValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("basic")
    private static final class BasicValue implements Value {
        @JsonUnwrapped
        private BasicCustomFiles value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private BasicValue() {}

        private BasicValue(BasicCustomFiles value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitBasic(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof BasicValue && equalTo((BasicValue) other);
        }

        private boolean equalTo(BasicValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CustomFiles{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("custom")
    private static final class CustomValue implements Value {
        @JsonProperty("value")
        private Map<Language, Files> value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private CustomValue(@JsonProperty("value") Map<Language, Files> value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitCustom(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof CustomValue && equalTo((CustomValue) other);
        }

        private boolean equalTo(CustomValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CustomFiles{" + "value: " + value + "}";
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
            return "CustomFiles{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
