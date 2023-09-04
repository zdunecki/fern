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

public final class TestCaseGrade {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private TestCaseGrade(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static TestCaseGrade hidden(TestCaseHiddenGrade value) {
        return new TestCaseGrade(new HiddenValue(value));
    }

    public static TestCaseGrade nonHidden(TestCaseNonHiddenGrade value) {
        return new TestCaseGrade(new NonHiddenValue(value));
    }

    public boolean isHidden() {
        return value instanceof HiddenValue;
    }

    public boolean isNonHidden() {
        return value instanceof NonHiddenValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<TestCaseHiddenGrade> getHidden() {
        if (isHidden()) {
            return Optional.of(((HiddenValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<TestCaseNonHiddenGrade> getNonHidden() {
        if (isNonHidden()) {
            return Optional.of(((NonHiddenValue) value).value);
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
        T visitHidden(TestCaseHiddenGrade hidden);

        T visitNonHidden(TestCaseNonHiddenGrade nonHidden);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(HiddenValue.class), @JsonSubTypes.Type(NonHiddenValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("hidden")
    private static final class HiddenValue implements Value {
        @JsonUnwrapped
        private TestCaseHiddenGrade value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private HiddenValue() {}

        private HiddenValue(TestCaseHiddenGrade value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitHidden(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof HiddenValue && equalTo((HiddenValue) other);
        }

        private boolean equalTo(HiddenValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "TestCaseGrade{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("nonHidden")
    private static final class NonHiddenValue implements Value {
        @JsonUnwrapped
        private TestCaseNonHiddenGrade value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private NonHiddenValue() {}

        private NonHiddenValue(TestCaseNonHiddenGrade value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitNonHidden(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof NonHiddenValue && equalTo((NonHiddenValue) other);
        }

        private boolean equalTo(NonHiddenValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "TestCaseGrade{" + "value: " + value + "}";
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
            return "TestCaseGrade{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
