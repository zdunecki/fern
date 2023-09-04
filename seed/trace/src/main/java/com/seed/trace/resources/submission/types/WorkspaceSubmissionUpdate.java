package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = WorkspaceSubmissionUpdate.Builder.class)
public final class WorkspaceSubmissionUpdate {
    private final OffsetDateTime updateTime;

    private final WorkspaceSubmissionUpdateInfo updateInfo;

    private WorkspaceSubmissionUpdate(OffsetDateTime updateTime, WorkspaceSubmissionUpdateInfo updateInfo) {
        this.updateTime = updateTime;
        this.updateInfo = updateInfo;
    }

    @JsonProperty("updateTime")
    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateInfo")
    public WorkspaceSubmissionUpdateInfo getUpdateInfo() {
        return updateInfo;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof WorkspaceSubmissionUpdate && equalTo((WorkspaceSubmissionUpdate) other);
    }

    private boolean equalTo(WorkspaceSubmissionUpdate other) {
        return updateTime.equals(other.updateTime) && updateInfo.equals(other.updateInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.updateTime, this.updateInfo);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static UpdateTimeStage builder() {
        return new Builder();
    }

    public interface UpdateTimeStage {
        UpdateInfoStage updateTime(OffsetDateTime updateTime);

        Builder from(WorkspaceSubmissionUpdate other);
    }

    public interface UpdateInfoStage {
        _FinalStage updateInfo(WorkspaceSubmissionUpdateInfo updateInfo);
    }

    public interface _FinalStage {
        WorkspaceSubmissionUpdate build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements UpdateTimeStage, UpdateInfoStage, _FinalStage {
        private OffsetDateTime updateTime;

        private WorkspaceSubmissionUpdateInfo updateInfo;

        private Builder() {}

        @Override
        public Builder from(WorkspaceSubmissionUpdate other) {
            updateTime(other.getUpdateTime());
            updateInfo(other.getUpdateInfo());
            return this;
        }

        @Override
        @JsonSetter("updateTime")
        public UpdateInfoStage updateTime(OffsetDateTime updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        @Override
        @JsonSetter("updateInfo")
        public _FinalStage updateInfo(WorkspaceSubmissionUpdateInfo updateInfo) {
            this.updateInfo = updateInfo;
            return this;
        }

        @Override
        public WorkspaceSubmissionUpdate build() {
            return new WorkspaceSubmissionUpdate(updateTime, updateInfo);
        }
    }
}
