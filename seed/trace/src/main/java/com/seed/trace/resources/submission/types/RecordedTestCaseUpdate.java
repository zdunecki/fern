package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = RecordedTestCaseUpdate.Builder.class)
public final class RecordedTestCaseUpdate {
    private final String testCaseId;

    private final int traceResponsesSize;

    private RecordedTestCaseUpdate(String testCaseId, int traceResponsesSize) {
        this.testCaseId = testCaseId;
        this.traceResponsesSize = traceResponsesSize;
    }

    @JsonProperty("testCaseId")
    public String getTestCaseId() {
        return testCaseId;
    }

    @JsonProperty("traceResponsesSize")
    public int getTraceResponsesSize() {
        return traceResponsesSize;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof RecordedTestCaseUpdate && equalTo((RecordedTestCaseUpdate) other);
    }

    private boolean equalTo(RecordedTestCaseUpdate other) {
        return testCaseId.equals(other.testCaseId) && traceResponsesSize == other.traceResponsesSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.testCaseId, this.traceResponsesSize);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TestCaseIdStage builder() {
        return new Builder();
    }

    public interface TestCaseIdStage {
        TraceResponsesSizeStage testCaseId(String testCaseId);

        Builder from(RecordedTestCaseUpdate other);
    }

    public interface TraceResponsesSizeStage {
        _FinalStage traceResponsesSize(int traceResponsesSize);
    }

    public interface _FinalStage {
        RecordedTestCaseUpdate build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TestCaseIdStage, TraceResponsesSizeStage, _FinalStage {
        private String testCaseId;

        private int traceResponsesSize;

        private Builder() {}

        @Override
        public Builder from(RecordedTestCaseUpdate other) {
            testCaseId(other.getTestCaseId());
            traceResponsesSize(other.getTraceResponsesSize());
            return this;
        }

        @Override
        @JsonSetter("testCaseId")
        public TraceResponsesSizeStage testCaseId(String testCaseId) {
            this.testCaseId = testCaseId;
            return this;
        }

        @Override
        @JsonSetter("traceResponsesSize")
        public _FinalStage traceResponsesSize(int traceResponsesSize) {
            this.traceResponsesSize = traceResponsesSize;
            return this;
        }

        @Override
        public RecordedTestCaseUpdate build() {
            return new RecordedTestCaseUpdate(testCaseId, traceResponsesSize);
        }
    }
}
