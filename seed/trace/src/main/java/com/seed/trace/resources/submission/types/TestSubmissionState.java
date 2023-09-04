package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = TestSubmissionState.Builder.class)
public final class TestSubmissionState {
    private final String problemId;

    private final List<TestCase> defaultTestCases;

    private final List<TestCase> customTestCases;

    private final TestSubmissionStatus status;

    private TestSubmissionState(
            String problemId,
            List<TestCase> defaultTestCases,
            List<TestCase> customTestCases,
            TestSubmissionStatus status) {
        this.problemId = problemId;
        this.defaultTestCases = defaultTestCases;
        this.customTestCases = customTestCases;
        this.status = status;
    }

    @JsonProperty("problemId")
    public String getProblemId() {
        return problemId;
    }

    @JsonProperty("defaultTestCases")
    public List<TestCase> getDefaultTestCases() {
        return defaultTestCases;
    }

    @JsonProperty("customTestCases")
    public List<TestCase> getCustomTestCases() {
        return customTestCases;
    }

    @JsonProperty("status")
    public TestSubmissionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TestSubmissionState && equalTo((TestSubmissionState) other);
    }

    private boolean equalTo(TestSubmissionState other) {
        return problemId.equals(other.problemId)
                && defaultTestCases.equals(other.defaultTestCases)
                && customTestCases.equals(other.customTestCases)
                && status.equals(other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.problemId, this.defaultTestCases, this.customTestCases, this.status);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ProblemIdStage builder() {
        return new Builder();
    }

    public interface ProblemIdStage {
        StatusStage problemId(String problemId);

        Builder from(TestSubmissionState other);
    }

    public interface StatusStage {
        _FinalStage status(TestSubmissionStatus status);
    }

    public interface _FinalStage {
        TestSubmissionState build();

        _FinalStage defaultTestCases(List<TestCase> defaultTestCases);

        _FinalStage addDefaultTestCases(TestCase defaultTestCases);

        _FinalStage addAllDefaultTestCases(List<TestCase> defaultTestCases);

        _FinalStage customTestCases(List<TestCase> customTestCases);

        _FinalStage addCustomTestCases(TestCase customTestCases);

        _FinalStage addAllCustomTestCases(List<TestCase> customTestCases);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ProblemIdStage, StatusStage, _FinalStage {
        private String problemId;

        private TestSubmissionStatus status;

        private List<TestCase> customTestCases = new ArrayList<>();

        private List<TestCase> defaultTestCases = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(TestSubmissionState other) {
            problemId(other.getProblemId());
            defaultTestCases(other.getDefaultTestCases());
            customTestCases(other.getCustomTestCases());
            status(other.getStatus());
            return this;
        }

        @Override
        @JsonSetter("problemId")
        public StatusStage problemId(String problemId) {
            this.problemId = problemId;
            return this;
        }

        @Override
        @JsonSetter("status")
        public _FinalStage status(TestSubmissionStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public _FinalStage addAllCustomTestCases(List<TestCase> customTestCases) {
            this.customTestCases.addAll(customTestCases);
            return this;
        }

        @Override
        public _FinalStage addCustomTestCases(TestCase customTestCases) {
            this.customTestCases.add(customTestCases);
            return this;
        }

        @Override
        @JsonSetter(value = "customTestCases", nulls = Nulls.SKIP)
        public _FinalStage customTestCases(List<TestCase> customTestCases) {
            this.customTestCases.clear();
            this.customTestCases.addAll(customTestCases);
            return this;
        }

        @Override
        public _FinalStage addAllDefaultTestCases(List<TestCase> defaultTestCases) {
            this.defaultTestCases.addAll(defaultTestCases);
            return this;
        }

        @Override
        public _FinalStage addDefaultTestCases(TestCase defaultTestCases) {
            this.defaultTestCases.add(defaultTestCases);
            return this;
        }

        @Override
        @JsonSetter(value = "defaultTestCases", nulls = Nulls.SKIP)
        public _FinalStage defaultTestCases(List<TestCase> defaultTestCases) {
            this.defaultTestCases.clear();
            this.defaultTestCases.addAll(defaultTestCases);
            return this;
        }

        @Override
        public TestSubmissionState build() {
            return new TestSubmissionState(problemId, defaultTestCases, customTestCases, status);
        }
    }
}
