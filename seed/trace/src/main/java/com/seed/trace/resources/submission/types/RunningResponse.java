/**
 * This file was auto-generated by Fern from our API Definition.
 */
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
@JsonDeserialize(builder = RunningResponse.Builder.class)
public final class RunningResponse {
    private final UUID submissionId;

    private final RunningSubmissionState state;

    private RunningResponse(UUID submissionId, RunningSubmissionState state) {
        this.submissionId = submissionId;
        this.state = state;
    }

    @JsonProperty("submissionId")
    public UUID getSubmissionId() {
        return submissionId;
    }

    @JsonProperty("state")
    public RunningSubmissionState getState() {
        return state;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof RunningResponse && equalTo((RunningResponse) other);
    }

    private boolean equalTo(RunningResponse other) {
        return submissionId.equals(other.submissionId) && state.equals(other.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.submissionId, this.state);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SubmissionIdStage builder() {
        return new Builder();
    }

    public interface SubmissionIdStage {
        StateStage submissionId(UUID submissionId);

        Builder from(RunningResponse other);
    }

    public interface StateStage {
        _FinalStage state(RunningSubmissionState state);
    }

    public interface _FinalStage {
        RunningResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SubmissionIdStage, StateStage, _FinalStage {
        private UUID submissionId;

        private RunningSubmissionState state;

        private Builder() {}

        @Override
        public Builder from(RunningResponse other) {
            submissionId(other.getSubmissionId());
            state(other.getState());
            return this;
        }

        @Override
        @JsonSetter("submissionId")
        public StateStage submissionId(UUID submissionId) {
            this.submissionId = submissionId;
            return this;
        }

        @Override
        @JsonSetter("state")
        public _FinalStage state(RunningSubmissionState state) {
            this.state = state;
            return this;
        }

        @Override
        public RunningResponse build() {
            return new RunningResponse(submissionId, state);
        }
    }
}
