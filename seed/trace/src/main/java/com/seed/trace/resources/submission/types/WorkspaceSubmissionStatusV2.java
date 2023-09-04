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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = WorkspaceSubmissionStatusV2.Builder.class)
public final class WorkspaceSubmissionStatusV2 {
    private final List<WorkspaceSubmissionUpdate> updates;

    private WorkspaceSubmissionStatusV2(List<WorkspaceSubmissionUpdate> updates) {
        this.updates = updates;
    }

    @JsonProperty("updates")
    public List<WorkspaceSubmissionUpdate> getUpdates() {
        return updates;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof WorkspaceSubmissionStatusV2 && equalTo((WorkspaceSubmissionStatusV2) other);
    }

    private boolean equalTo(WorkspaceSubmissionStatusV2 other) {
        return updates.equals(other.updates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.updates);
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
        private List<WorkspaceSubmissionUpdate> updates = new ArrayList<>();

        private Builder() {}

        public Builder from(WorkspaceSubmissionStatusV2 other) {
            updates(other.getUpdates());
            return this;
        }

        @JsonSetter(value = "updates", nulls = Nulls.SKIP)
        public Builder updates(List<WorkspaceSubmissionUpdate> updates) {
            this.updates.clear();
            this.updates.addAll(updates);
            return this;
        }

        public Builder addUpdates(WorkspaceSubmissionUpdate updates) {
            this.updates.add(updates);
            return this;
        }

        public Builder addAllUpdates(List<WorkspaceSubmissionUpdate> updates) {
            this.updates.addAll(updates);
            return this;
        }

        public WorkspaceSubmissionStatusV2 build() {
            return new WorkspaceSubmissionStatusV2(updates);
        }
    }
}
