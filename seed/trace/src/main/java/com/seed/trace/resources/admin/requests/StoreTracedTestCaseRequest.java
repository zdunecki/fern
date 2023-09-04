package com.seed.trace.resources.admin.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.submission.types.TestCaseResultWithStdout;
import com.seed.trace.resources.submission.types.TraceResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = StoreTracedTestCaseRequest.Builder.class)
public final class StoreTracedTestCaseRequest {
    private final TestCaseResultWithStdout result;

    private final List<TraceResponse> traceResponses;

    private StoreTracedTestCaseRequest(TestCaseResultWithStdout result, List<TraceResponse> traceResponses) {
        this.result = result;
        this.traceResponses = traceResponses;
    }

    @JsonProperty("result")
    public TestCaseResultWithStdout getResult() {
        return result;
    }

    @JsonProperty("traceResponses")
    public List<TraceResponse> getTraceResponses() {
        return traceResponses;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof StoreTracedTestCaseRequest && equalTo((StoreTracedTestCaseRequest) other);
    }

    private boolean equalTo(StoreTracedTestCaseRequest other) {
        return result.equals(other.result) && traceResponses.equals(other.traceResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.result, this.traceResponses);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ResultStage builder() {
        return new Builder();
    }

    public interface ResultStage {
        _FinalStage result(TestCaseResultWithStdout result);

        Builder from(StoreTracedTestCaseRequest other);
    }

    public interface _FinalStage {
        StoreTracedTestCaseRequest build();

        _FinalStage traceResponses(List<TraceResponse> traceResponses);

        _FinalStage addTraceResponses(TraceResponse traceResponses);

        _FinalStage addAllTraceResponses(List<TraceResponse> traceResponses);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ResultStage, _FinalStage {
        private TestCaseResultWithStdout result;

        private List<TraceResponse> traceResponses = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(StoreTracedTestCaseRequest other) {
            result(other.getResult());
            traceResponses(other.getTraceResponses());
            return this;
        }

        @Override
        @JsonSetter("result")
        public _FinalStage result(TestCaseResultWithStdout result) {
            this.result = result;
            return this;
        }

        @Override
        public _FinalStage addAllTraceResponses(List<TraceResponse> traceResponses) {
            this.traceResponses.addAll(traceResponses);
            return this;
        }

        @Override
        public _FinalStage addTraceResponses(TraceResponse traceResponses) {
            this.traceResponses.add(traceResponses);
            return this;
        }

        @Override
        @JsonSetter(value = "traceResponses", nulls = Nulls.SKIP)
        public _FinalStage traceResponses(List<TraceResponse> traceResponses) {
            this.traceResponses.clear();
            this.traceResponses.addAll(traceResponses);
            return this;
        }

        @Override
        public StoreTracedTestCaseRequest build() {
            return new StoreTracedTestCaseRequest(result, traceResponses);
        }
    }
}
