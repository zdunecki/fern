package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = BuildingExecutorResponse.Builder.class)
public final class BuildingExecutorResponse {
    private final UUID submissionId;

    private final ExecutionSessionStatus status;

    private BuildingExecutorResponse(UUID submissionId, ExecutionSessionStatus status) {
        this.submissionId = submissionId;
        this.status = status;
    }

    @JsonProperty("submissionId")
    public UUID getSubmissionId() {
        return submissionId;
    }

    @JsonProperty("status")
    public ExecutionSessionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BuildingExecutorResponse && equalTo((BuildingExecutorResponse) other);
    }

    private boolean equalTo(BuildingExecutorResponse other) {
        return submissionId.equals(other.submissionId) && status.equals(other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.submissionId, this.status);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SubmissionIdStage builder() {
        return new Builder();
    }

    public interface SubmissionIdStage {
        StatusStage submissionId(UUID submissionId);

        Builder from(BuildingExecutorResponse other);
    }

    public interface StatusStage {
        _FinalStage status(ExecutionSessionStatus status);
    }

    public interface _FinalStage {
        BuildingExecutorResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SubmissionIdStage, StatusStage, _FinalStage {
        private UUID submissionId;

        private ExecutionSessionStatus status;

        private Builder() {}

        @Override
        public Builder from(BuildingExecutorResponse other) {
            submissionId(other.getSubmissionId());
            status(other.getStatus());
            return this;
        }

        @Override
        @JsonSetter("submissionId")
        public StatusStage submissionId(UUID submissionId) {
            this.submissionId = submissionId;
            return this;
        }

        @Override
        @JsonSetter("status")
        public _FinalStage status(ExecutionSessionStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public BuildingExecutorResponse build() {
            return new BuildingExecutorResponse(submissionId, status);
        }
    }
}
