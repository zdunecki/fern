package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = StackInformation.Builder.class)
public final class StackInformation {
    private final int numStackFrames;

    private final Optional<StackFrame> topStackFrame;

    private StackInformation(int numStackFrames, Optional<StackFrame> topStackFrame) {
        this.numStackFrames = numStackFrames;
        this.topStackFrame = topStackFrame;
    }

    @JsonProperty("numStackFrames")
    public int getNumStackFrames() {
        return numStackFrames;
    }

    @JsonProperty("topStackFrame")
    public Optional<StackFrame> getTopStackFrame() {
        return topStackFrame;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof StackInformation && equalTo((StackInformation) other);
    }

    private boolean equalTo(StackInformation other) {
        return numStackFrames == other.numStackFrames && topStackFrame.equals(other.topStackFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.numStackFrames, this.topStackFrame);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static NumStackFramesStage builder() {
        return new Builder();
    }

    public interface NumStackFramesStage {
        _FinalStage numStackFrames(int numStackFrames);

        Builder from(StackInformation other);
    }

    public interface _FinalStage {
        StackInformation build();

        _FinalStage topStackFrame(Optional<StackFrame> topStackFrame);

        _FinalStage topStackFrame(StackFrame topStackFrame);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements NumStackFramesStage, _FinalStage {
        private int numStackFrames;

        private Optional<StackFrame> topStackFrame = Optional.empty();

        private Builder() {}

        @Override
        public Builder from(StackInformation other) {
            numStackFrames(other.getNumStackFrames());
            topStackFrame(other.getTopStackFrame());
            return this;
        }

        @Override
        @JsonSetter("numStackFrames")
        public _FinalStage numStackFrames(int numStackFrames) {
            this.numStackFrames = numStackFrames;
            return this;
        }

        @Override
        public _FinalStage topStackFrame(StackFrame topStackFrame) {
            this.topStackFrame = Optional.of(topStackFrame);
            return this;
        }

        @Override
        @JsonSetter(value = "topStackFrame", nulls = Nulls.SKIP)
        public _FinalStage topStackFrame(Optional<StackFrame> topStackFrame) {
            this.topStackFrame = topStackFrame;
            return this;
        }

        @Override
        public StackInformation build() {
            return new StackInformation(numStackFrames, topStackFrame);
        }
    }
}
