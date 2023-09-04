package com.seed.trace.resources.v2.v3.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = TestCaseImplementationDescription.Builder.class)
public final class TestCaseImplementationDescription {
    private final List<TestCaseImplementationDescriptionBoard> boards;

    private TestCaseImplementationDescription(List<TestCaseImplementationDescriptionBoard> boards) {
        this.boards = boards;
    }

    @JsonProperty("boards")
    public List<TestCaseImplementationDescriptionBoard> getBoards() {
        return boards;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TestCaseImplementationDescription && equalTo((TestCaseImplementationDescription) other);
    }

    private boolean equalTo(TestCaseImplementationDescription other) {
        return boards.equals(other.boards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.boards);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private List<TestCaseImplementationDescriptionBoard> boards = new ArrayList<>();

        private Builder() {}

        public Builder from(TestCaseImplementationDescription other) {
            boards(other.getBoards());
            return this;
        }

        @JsonSetter(value = "boards", nulls = Nulls.SKIP)
        public Builder boards(List<TestCaseImplementationDescriptionBoard> boards) {
            this.boards.clear();
            this.boards.addAll(boards);
            return this;
        }

        public Builder addBoards(TestCaseImplementationDescriptionBoard boards) {
            this.boards.add(boards);
            return this;
        }

        public Builder addAllBoards(List<TestCaseImplementationDescriptionBoard> boards) {
            this.boards.addAll(boards);
            return this;
        }

        public TestCaseImplementationDescription build() {
            return new TestCaseImplementationDescription(boards);
        }
    }
}
