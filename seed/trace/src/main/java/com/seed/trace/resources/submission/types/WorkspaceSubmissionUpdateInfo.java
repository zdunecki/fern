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

public final class WorkspaceSubmissionUpdateInfo {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private WorkspaceSubmissionUpdateInfo(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static WorkspaceSubmissionUpdateInfo running(RunningSubmissionState value) {
        return new WorkspaceSubmissionUpdateInfo(new RunningValue(value));
    }

    public static WorkspaceSubmissionUpdateInfo ran(WorkspaceRunDetails value) {
        return new WorkspaceSubmissionUpdateInfo(new RanValue(value));
    }

    public static WorkspaceSubmissionUpdateInfo stopped() {
        return new WorkspaceSubmissionUpdateInfo(new StoppedValue());
    }

    public static WorkspaceSubmissionUpdateInfo traced() {
        return new WorkspaceSubmissionUpdateInfo(new TracedValue());
    }

    public static WorkspaceSubmissionUpdateInfo tracedV2(WorkspaceTracedUpdate value) {
        return new WorkspaceSubmissionUpdateInfo(new TracedV2Value(value));
    }

    public static WorkspaceSubmissionUpdateInfo errored(ErrorInfo value) {
        return new WorkspaceSubmissionUpdateInfo(new ErroredValue(value));
    }

    public static WorkspaceSubmissionUpdateInfo finished() {
        return new WorkspaceSubmissionUpdateInfo(new FinishedValue());
    }

    public boolean isRunning() {
        return value instanceof RunningValue;
    }

    public boolean isRan() {
        return value instanceof RanValue;
    }

    public boolean isStopped() {
        return value instanceof StoppedValue;
    }

    public boolean isTraced() {
        return value instanceof TracedValue;
    }

    public boolean isTracedV2() {
        return value instanceof TracedV2Value;
    }

    public boolean isErrored() {
        return value instanceof ErroredValue;
    }

    public boolean isFinished() {
        return value instanceof FinishedValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<RunningSubmissionState> getRunning() {
        if (isRunning()) {
            return Optional.of(((RunningValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<WorkspaceRunDetails> getRan() {
        if (isRan()) {
            return Optional.of(((RanValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<WorkspaceTracedUpdate> getTracedV2() {
        if (isTracedV2()) {
            return Optional.of(((TracedV2Value) value).value);
        }
        return Optional.empty();
    }

    public Optional<ErrorInfo> getErrored() {
        if (isErrored()) {
            return Optional.of(((ErroredValue) value).value);
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
        T visitRunning(RunningSubmissionState running);

        T visitRan(WorkspaceRunDetails ran);

        T visitStopped();

        T visitTraced();

        T visitTracedV2(WorkspaceTracedUpdate tracedV2);

        T visitErrored(ErrorInfo errored);

        T visitFinished();

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(RunningValue.class),
        @JsonSubTypes.Type(RanValue.class),
        @JsonSubTypes.Type(StoppedValue.class),
        @JsonSubTypes.Type(TracedValue.class),
        @JsonSubTypes.Type(TracedV2Value.class),
        @JsonSubTypes.Type(ErroredValue.class),
        @JsonSubTypes.Type(FinishedValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("running")
    private static final class RunningValue implements Value {
        @JsonProperty("value")
        private RunningSubmissionState value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private RunningValue(@JsonProperty("value") RunningSubmissionState value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitRunning(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof RunningValue && equalTo((RunningValue) other);
        }

        private boolean equalTo(RunningValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("ran")
    private static final class RanValue implements Value {
        @JsonUnwrapped
        private WorkspaceRunDetails value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private RanValue() {}

        private RanValue(WorkspaceRunDetails value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitRan(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof RanValue && equalTo((RanValue) other);
        }

        private boolean equalTo(RanValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("stopped")
    private static final class StoppedValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private StoppedValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitStopped();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof StoppedValue;
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "}";
        }
    }

    @JsonTypeName("traced")
    private static final class TracedValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private TracedValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitTraced();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof TracedValue;
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "}";
        }
    }

    @JsonTypeName("tracedV2")
    private static final class TracedV2Value implements Value {
        @JsonUnwrapped
        private WorkspaceTracedUpdate value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private TracedV2Value() {}

        private TracedV2Value(WorkspaceTracedUpdate value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitTracedV2(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof TracedV2Value && equalTo((TracedV2Value) other);
        }

        private boolean equalTo(TracedV2Value other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("errored")
    private static final class ErroredValue implements Value {
        @JsonProperty("value")
        private ErrorInfo value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private ErroredValue(@JsonProperty("value") ErrorInfo value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitErrored(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof ErroredValue && equalTo((ErroredValue) other);
        }

        private boolean equalTo(ErroredValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("finished")
    private static final class FinishedValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private FinishedValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitFinished();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof FinishedValue;
        }

        @Override
        public String toString() {
            return "WorkspaceSubmissionUpdateInfo{" + "}";
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
            return "WorkspaceSubmissionUpdateInfo{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
