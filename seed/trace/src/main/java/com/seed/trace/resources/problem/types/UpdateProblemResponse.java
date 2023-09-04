package com.seed.trace.resources.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = UpdateProblemResponse.Builder.class)
public final class UpdateProblemResponse {
    private final int problemVersion;

    private UpdateProblemResponse(int problemVersion) {
        this.problemVersion = problemVersion;
    }

    @JsonProperty("problemVersion")
    public int getProblemVersion() {
        return problemVersion;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof UpdateProblemResponse && equalTo((UpdateProblemResponse) other);
    }

    private boolean equalTo(UpdateProblemResponse other) {
        return problemVersion == other.problemVersion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.problemVersion);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ProblemVersionStage builder() {
        return new Builder();
    }

    public interface ProblemVersionStage {
        _FinalStage problemVersion(int problemVersion);

        Builder from(UpdateProblemResponse other);
    }

    public interface _FinalStage {
        UpdateProblemResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ProblemVersionStage, _FinalStage {
        private int problemVersion;

        private Builder() {}

        @Override
        public Builder from(UpdateProblemResponse other) {
            problemVersion(other.getProblemVersion());
            return this;
        }

        @Override
        @JsonSetter("problemVersion")
        public _FinalStage problemVersion(int problemVersion) {
            this.problemVersion = problemVersion;
            return this;
        }

        @Override
        public UpdateProblemResponse build() {
            return new UpdateProblemResponse(problemVersion);
        }
    }
}
