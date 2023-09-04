package com.seed.trace.resources.v2.v3.problem.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;

public final class TestCaseImplementationDescriptionBoard {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private TestCaseImplementationDescriptionBoard(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static TestCaseImplementationDescriptionBoard html(String value) {
        return new TestCaseImplementationDescriptionBoard(new HtmlValue(value));
    }

    public static TestCaseImplementationDescriptionBoard paramId(String value) {
        return new TestCaseImplementationDescriptionBoard(new ParamIdValue(value));
    }

    public boolean isHtml() {
        return value instanceof HtmlValue;
    }

    public boolean isParamId() {
        return value instanceof ParamIdValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<String> getHtml() {
        if (isHtml()) {
            return Optional.of(((HtmlValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<String> getParamId() {
        if (isParamId()) {
            return Optional.of(((ParamIdValue) value).value);
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
        T visitHtml(String html);

        T visitParamId(String paramId);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(HtmlValue.class), @JsonSubTypes.Type(ParamIdValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("html")
    private static final class HtmlValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private HtmlValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitHtml(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof HtmlValue && equalTo((HtmlValue) other);
        }

        private boolean equalTo(HtmlValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "TestCaseImplementationDescriptionBoard{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("paramId")
    private static final class ParamIdValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private ParamIdValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitParamId(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof ParamIdValue && equalTo((ParamIdValue) other);
        }

        private boolean equalTo(ParamIdValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "TestCaseImplementationDescriptionBoard{" + "value: " + value + "}";
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
            return "TestCaseImplementationDescriptionBoard{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
