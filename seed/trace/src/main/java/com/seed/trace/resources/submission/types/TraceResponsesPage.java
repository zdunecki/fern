package com.seed.trace.resources.submission.types;

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
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = TraceResponsesPage.Builder.class)
public final class TraceResponsesPage {
    private final Optional<Integer> offset;

    private final List<TraceResponse> traceResponses;

    private TraceResponsesPage(Optional<Integer> offset, List<TraceResponse> traceResponses) {
        this.offset = offset;
        this.traceResponses = traceResponses;
    }

    /**
     * @return If present, use this to load subseqent pages.
     * The offset is the id of the next trace response to load.
     */
    @JsonProperty("offset")
    public Optional<Integer> getOffset() {
        return offset;
    }

    @JsonProperty("traceResponses")
    public List<TraceResponse> getTraceResponses() {
        return traceResponses;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TraceResponsesPage && equalTo((TraceResponsesPage) other);
    }

    private boolean equalTo(TraceResponsesPage other) {
        return offset.equals(other.offset) && traceResponses.equals(other.traceResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.offset, this.traceResponses);
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
        private Optional<Integer> offset = Optional.empty();

        private List<TraceResponse> traceResponses = new ArrayList<>();

        private Builder() {}

        public Builder from(TraceResponsesPage other) {
            offset(other.getOffset());
            traceResponses(other.getTraceResponses());
            return this;
        }

        @JsonSetter(value = "offset", nulls = Nulls.SKIP)
        public Builder offset(Optional<Integer> offset) {
            this.offset = offset;
            return this;
        }

        public Builder offset(Integer offset) {
            this.offset = Optional.of(offset);
            return this;
        }

        @JsonSetter(value = "traceResponses", nulls = Nulls.SKIP)
        public Builder traceResponses(List<TraceResponse> traceResponses) {
            this.traceResponses.clear();
            this.traceResponses.addAll(traceResponses);
            return this;
        }

        public Builder addTraceResponses(TraceResponse traceResponses) {
            this.traceResponses.add(traceResponses);
            return this;
        }

        public Builder addAllTraceResponses(List<TraceResponse> traceResponses) {
            this.traceResponses.addAll(traceResponses);
            return this;
        }

        public TraceResponsesPage build() {
            return new TraceResponsesPage(offset, traceResponses);
        }
    }
}
