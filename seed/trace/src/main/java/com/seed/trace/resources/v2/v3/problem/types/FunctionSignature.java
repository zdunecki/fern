package com.seed.trace.resources.v2.v3.problem.types;

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

public final class FunctionSignature {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private FunctionSignature(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static FunctionSignature void_(VoidFunctionSignature value) {
        return new FunctionSignature(new VoidValue(value));
    }

    public static FunctionSignature nonVoid(NonVoidFunctionSignature value) {
        return new FunctionSignature(new NonVoidValue(value));
    }

    public static FunctionSignature voidThatTakesActualResult(VoidFunctionSignatureThatTakesActualResult value) {
        return new FunctionSignature(new VoidThatTakesActualResultValue(value));
    }

    public boolean isVoid() {
        return value instanceof VoidValue;
    }

    public boolean isNonVoid() {
        return value instanceof NonVoidValue;
    }

    public boolean isVoidThatTakesActualResult() {
        return value instanceof VoidThatTakesActualResultValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<VoidFunctionSignature> getVoid() {
        if (isVoid()) {
            return Optional.of(((VoidValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<NonVoidFunctionSignature> getNonVoid() {
        if (isNonVoid()) {
            return Optional.of(((NonVoidValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<VoidFunctionSignatureThatTakesActualResult> getVoidThatTakesActualResult() {
        if (isVoidThatTakesActualResult()) {
            return Optional.of(((VoidThatTakesActualResultValue) value).value);
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
        T visitVoid(VoidFunctionSignature void_);

        T visitNonVoid(NonVoidFunctionSignature nonVoid);

        T visitVoidThatTakesActualResult(VoidFunctionSignatureThatTakesActualResult voidThatTakesActualResult);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(VoidValue.class),
        @JsonSubTypes.Type(NonVoidValue.class),
        @JsonSubTypes.Type(VoidThatTakesActualResultValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("void")
    private static final class VoidValue implements Value {
        @JsonUnwrapped
        private VoidFunctionSignature value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private VoidValue() {}

        private VoidValue(VoidFunctionSignature value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitVoid(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof VoidValue && equalTo((VoidValue) other);
        }

        private boolean equalTo(VoidValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "FunctionSignature{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("nonVoid")
    private static final class NonVoidValue implements Value {
        @JsonUnwrapped
        private NonVoidFunctionSignature value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private NonVoidValue() {}

        private NonVoidValue(NonVoidFunctionSignature value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitNonVoid(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof NonVoidValue && equalTo((NonVoidValue) other);
        }

        private boolean equalTo(NonVoidValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "FunctionSignature{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("voidThatTakesActualResult")
    private static final class VoidThatTakesActualResultValue implements Value {
        @JsonUnwrapped
        private VoidFunctionSignatureThatTakesActualResult value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private VoidThatTakesActualResultValue() {}

        private VoidThatTakesActualResultValue(VoidFunctionSignatureThatTakesActualResult value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitVoidThatTakesActualResult(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof VoidThatTakesActualResultValue && equalTo((VoidThatTakesActualResultValue) other);
        }

        private boolean equalTo(VoidThatTakesActualResultValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "FunctionSignature{" + "value: " + value + "}";
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
            return "FunctionSignature{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
