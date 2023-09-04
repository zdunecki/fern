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

public final class CodeExecutionUpdate {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private CodeExecutionUpdate(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static CodeExecutionUpdate buildingExecutor(BuildingExecutorResponse value) {
        return new CodeExecutionUpdate(new BuildingExecutorValue(value));
    }

    public static CodeExecutionUpdate running(RunningResponse value) {
        return new CodeExecutionUpdate(new RunningValue(value));
    }

    public static CodeExecutionUpdate errored(ErroredResponse value) {
        return new CodeExecutionUpdate(new ErroredValue(value));
    }

    public static CodeExecutionUpdate stopped(StoppedResponse value) {
        return new CodeExecutionUpdate(new StoppedValue(value));
    }

    public static CodeExecutionUpdate graded(GradedResponse value) {
        return new CodeExecutionUpdate(new GradedValue(value));
    }

    public static CodeExecutionUpdate gradedV2(GradedResponseV2 value) {
        return new CodeExecutionUpdate(new GradedV2Value(value));
    }

    public static CodeExecutionUpdate workspaceRan(WorkspaceRanResponse value) {
        return new CodeExecutionUpdate(new WorkspaceRanValue(value));
    }

    public static CodeExecutionUpdate recording(RecordingResponseNotification value) {
        return new CodeExecutionUpdate(new RecordingValue(value));
    }

    public static CodeExecutionUpdate recorded(RecordedResponseNotification value) {
        return new CodeExecutionUpdate(new RecordedValue(value));
    }

    public static CodeExecutionUpdate invalidRequest(InvalidRequestResponse value) {
        return new CodeExecutionUpdate(new InvalidRequestValue(value));
    }

    public static CodeExecutionUpdate finished(FinishedResponse value) {
        return new CodeExecutionUpdate(new FinishedValue(value));
    }

    public boolean isBuildingExecutor() {
        return value instanceof BuildingExecutorValue;
    }

    public boolean isRunning() {
        return value instanceof RunningValue;
    }

    public boolean isErrored() {
        return value instanceof ErroredValue;
    }

    public boolean isStopped() {
        return value instanceof StoppedValue;
    }

    public boolean isGraded() {
        return value instanceof GradedValue;
    }

    public boolean isGradedV2() {
        return value instanceof GradedV2Value;
    }

    public boolean isWorkspaceRan() {
        return value instanceof WorkspaceRanValue;
    }

    public boolean isRecording() {
        return value instanceof RecordingValue;
    }

    public boolean isRecorded() {
        return value instanceof RecordedValue;
    }

    public boolean isInvalidRequest() {
        return value instanceof InvalidRequestValue;
    }

    public boolean isFinished() {
        return value instanceof FinishedValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<BuildingExecutorResponse> getBuildingExecutor() {
        if (isBuildingExecutor()) {
            return Optional.of(((BuildingExecutorValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<RunningResponse> getRunning() {
        if (isRunning()) {
            return Optional.of(((RunningValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<ErroredResponse> getErrored() {
        if (isErrored()) {
            return Optional.of(((ErroredValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<StoppedResponse> getStopped() {
        if (isStopped()) {
            return Optional.of(((StoppedValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<GradedResponse> getGraded() {
        if (isGraded()) {
            return Optional.of(((GradedValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<GradedResponseV2> getGradedV2() {
        if (isGradedV2()) {
            return Optional.of(((GradedV2Value) value).value);
        }
        return Optional.empty();
    }

    public Optional<WorkspaceRanResponse> getWorkspaceRan() {
        if (isWorkspaceRan()) {
            return Optional.of(((WorkspaceRanValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<RecordingResponseNotification> getRecording() {
        if (isRecording()) {
            return Optional.of(((RecordingValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<RecordedResponseNotification> getRecorded() {
        if (isRecorded()) {
            return Optional.of(((RecordedValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<InvalidRequestResponse> getInvalidRequest() {
        if (isInvalidRequest()) {
            return Optional.of(((InvalidRequestValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<FinishedResponse> getFinished() {
        if (isFinished()) {
            return Optional.of(((FinishedValue) value).value);
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
        T visitBuildingExecutor(BuildingExecutorResponse buildingExecutor);

        T visitRunning(RunningResponse running);

        T visitErrored(ErroredResponse errored);

        T visitStopped(StoppedResponse stopped);

        T visitGraded(GradedResponse graded);

        T visitGradedV2(GradedResponseV2 gradedV2);

        T visitWorkspaceRan(WorkspaceRanResponse workspaceRan);

        T visitRecording(RecordingResponseNotification recording);

        T visitRecorded(RecordedResponseNotification recorded);

        T visitInvalidRequest(InvalidRequestResponse invalidRequest);

        T visitFinished(FinishedResponse finished);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(BuildingExecutorValue.class),
        @JsonSubTypes.Type(RunningValue.class),
        @JsonSubTypes.Type(ErroredValue.class),
        @JsonSubTypes.Type(StoppedValue.class),
        @JsonSubTypes.Type(GradedValue.class),
        @JsonSubTypes.Type(GradedV2Value.class),
        @JsonSubTypes.Type(WorkspaceRanValue.class),
        @JsonSubTypes.Type(RecordingValue.class),
        @JsonSubTypes.Type(RecordedValue.class),
        @JsonSubTypes.Type(InvalidRequestValue.class),
        @JsonSubTypes.Type(FinishedValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("buildingExecutor")
    private static final class BuildingExecutorValue implements Value {
        @JsonUnwrapped
        private BuildingExecutorResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private BuildingExecutorValue() {}

        private BuildingExecutorValue(BuildingExecutorResponse value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitBuildingExecutor(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof BuildingExecutorValue && equalTo((BuildingExecutorValue) other);
        }

        private boolean equalTo(BuildingExecutorValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("running")
    private static final class RunningValue implements Value {
        @JsonUnwrapped
        private RunningResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private RunningValue() {}

        private RunningValue(RunningResponse value) {
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
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("errored")
    private static final class ErroredValue implements Value {
        @JsonUnwrapped
        private ErroredResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private ErroredValue() {}

        private ErroredValue(ErroredResponse value) {
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
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("stopped")
    private static final class StoppedValue implements Value {
        @JsonUnwrapped
        private StoppedResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private StoppedValue() {}

        private StoppedValue(StoppedResponse value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitStopped(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof StoppedValue && equalTo((StoppedValue) other);
        }

        private boolean equalTo(StoppedValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("graded")
    private static final class GradedValue implements Value {
        @JsonUnwrapped
        private GradedResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GradedValue() {}

        private GradedValue(GradedResponse value) {
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
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("gradedV2")
    private static final class GradedV2Value implements Value {
        @JsonUnwrapped
        private GradedResponseV2 value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GradedV2Value() {}

        private GradedV2Value(GradedResponseV2 value) {
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
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("workspaceRan")
    private static final class WorkspaceRanValue implements Value {
        @JsonUnwrapped
        private WorkspaceRanResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private WorkspaceRanValue() {}

        private WorkspaceRanValue(WorkspaceRanResponse value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitWorkspaceRan(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof WorkspaceRanValue && equalTo((WorkspaceRanValue) other);
        }

        private boolean equalTo(WorkspaceRanValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("recording")
    private static final class RecordingValue implements Value {
        @JsonUnwrapped
        private RecordingResponseNotification value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private RecordingValue() {}

        private RecordingValue(RecordingResponseNotification value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitRecording(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof RecordingValue && equalTo((RecordingValue) other);
        }

        private boolean equalTo(RecordingValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("recorded")
    private static final class RecordedValue implements Value {
        @JsonUnwrapped
        private RecordedResponseNotification value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private RecordedValue() {}

        private RecordedValue(RecordedResponseNotification value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitRecorded(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof RecordedValue && equalTo((RecordedValue) other);
        }

        private boolean equalTo(RecordedValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("invalidRequest")
    private static final class InvalidRequestValue implements Value {
        @JsonUnwrapped
        private InvalidRequestResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private InvalidRequestValue() {}

        private InvalidRequestValue(InvalidRequestResponse value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitInvalidRequest(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof InvalidRequestValue && equalTo((InvalidRequestValue) other);
        }

        private boolean equalTo(InvalidRequestValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("finished")
    private static final class FinishedValue implements Value {
        @JsonUnwrapped
        private FinishedResponse value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private FinishedValue() {}

        private FinishedValue(FinishedResponse value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitFinished(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof FinishedValue && equalTo((FinishedValue) other);
        }

        private boolean equalTo(FinishedValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "CodeExecutionUpdate{" + "value: " + value + "}";
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
            return "CodeExecutionUpdate{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
