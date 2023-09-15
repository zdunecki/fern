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
@JsonDeserialize(builder = StoppedResponse.Builder.class)
public final class StoppedResponse {
    private final UUID submissionId;

    private StoppedResponse(UUID submissionId) {
        this.submissionId = submissionId;
    }

    @JsonProperty("submissionId")
    public UUID getSubmissionId() {
        return submissionId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof StoppedResponse && equalTo((StoppedResponse) other);
    }

    private boolean equalTo(StoppedResponse other) {
        return submissionId.equals(other.submissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.submissionId);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SubmissionIdStage builder() {
        return new Builder();
    }

    public interface SubmissionIdStage {
        _FinalStage submissionId(UUID submissionId);

        Builder from(StoppedResponse other);
    }

    public interface _FinalStage {
        StoppedResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SubmissionIdStage, _FinalStage {
        private UUID submissionId;

        private Builder() {}

        @Override
        public Builder from(StoppedResponse other) {
            submissionId(other.getSubmissionId());
            return this;
        }

        @Override
        @JsonSetter("submissionId")
        public _FinalStage submissionId(UUID submissionId) {
            this.submissionId = submissionId;
            return this;
        }

        @Override
        public StoppedResponse build() {
            return new StoppedResponse(submissionId);
        }
    }
}
