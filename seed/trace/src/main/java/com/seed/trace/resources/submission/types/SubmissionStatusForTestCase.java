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

public final class SubmissionStatusForTestCase {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private SubmissionStatusForTestCase(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static SubmissionStatusForTestCase graded(TestCaseResultWithStdout value) {
        return new SubmissionStatusForTestCase(new GradedValue(value));
    }

    public static SubmissionStatusForTestCase gradedV2(TestCaseGrade value) {
        return new SubmissionStatusForTestCase(new GradedV2Value(value));
    }

    public static SubmissionStatusForTestCase traced(TracedTestCase value) {
        return new SubmissionStatusForTestCase(new TracedValue(value));
    }

    public boolean isGraded() {
        return value instanceof GradedValue;
    }

    public boolean isGradedV2() {
        return value instanceof GradedV2Value;
    }

    public boolean isTraced() {
        return value instanceof TracedValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<TestCaseResultWithStdout> getGraded() {
        if (isGraded()) {
            return Optional.of(((GradedValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<TestCaseGrade> getGradedV2() {
        if (isGradedV2()) {
            return Optional.of(((GradedV2Value) value).value);
        }
        return Optional.empty();
    }

    public Optional<TracedTestCase> getTraced() {
        if (isTraced()) {
            return Optional.of(((TracedValue) value).value);
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
        T visitGraded(TestCaseResultWithStdout graded);

        T visitGradedV2(TestCaseGrade gradedV2);

        T visitTraced(TracedTestCase traced);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(GradedValue.class),
        @JsonSubTypes.Type(GradedV2Value.class),
        @JsonSubTypes.Type(TracedValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("graded")
    private static final class GradedValue implements Value {
        @JsonUnwrapped
        private TestCaseResultWithStdout value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GradedValue() {}

        private GradedValue(TestCaseResultWithStdout value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitGraded(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof GradedValue && equalTo((GradedValue) other);
        }

        private boolean equalTo(GradedValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "SubmissionStatusForTestCase{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("gradedV2")
    private static final class GradedV2Value implements Value {
        @JsonProperty("value")
        private TestCaseGrade value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GradedV2Value(@JsonProperty("value") TestCaseGrade value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitGradedV2(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof GradedV2Value && equalTo((GradedV2Value) other);
        }

        private boolean equalTo(GradedV2Value other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "SubmissionStatusForTestCase{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("traced")
    private static final class TracedValue implements Value {
        @JsonUnwrapped
        private TracedTestCase value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private TracedValue() {}

        private TracedValue(TracedTestCase value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitTraced(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof TracedValue && equalTo((TracedValue) other);
        }

        private boolean equalTo(TracedValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "SubmissionStatusForTestCase{" + "value: " + value + "}";
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
            return "SubmissionStatusForTestCase{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
