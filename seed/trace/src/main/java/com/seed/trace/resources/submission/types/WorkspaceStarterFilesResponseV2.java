package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.Language;
import com.seed.trace.resources.v2.problem.types.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = WorkspaceStarterFilesResponseV2.Builder.class)
public final class WorkspaceStarterFilesResponseV2 {
    private final Map<Language, Files> filesByLanguage;

    private WorkspaceStarterFilesResponseV2(Map<Language, Files> filesByLanguage) {
        this.filesByLanguage = filesByLanguage;
    }

    @JsonProperty("filesByLanguage")
    public Map<Language, Files> getFilesByLanguage() {
        return filesByLanguage;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof WorkspaceStarterFilesResponseV2 && equalTo((WorkspaceStarterFilesResponseV2) other);
    }

    private boolean equalTo(WorkspaceStarterFilesResponseV2 other) {
        return filesByLanguage.equals(other.filesByLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.filesByLanguage);
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
        private Map<Language, Files> filesByLanguage = new LinkedHashMap<>();

        private Builder() {}

        public Builder from(WorkspaceStarterFilesResponseV2 other) {
            filesByLanguage(other.getFilesByLanguage());
            return this;
        }

        @JsonSetter(value = "filesByLanguage", nulls = Nulls.SKIP)
        public Builder filesByLanguage(Map<Language, Files> filesByLanguage) {
            this.filesByLanguage.clear();
            this.filesByLanguage.putAll(filesByLanguage);
            return this;
        }

        public Builder putAllFilesByLanguage(Map<Language, Files> filesByLanguage) {
            this.filesByLanguage.putAll(filesByLanguage);
            return this;
        }

        public Builder filesByLanguage(Language key, Files value) {
            this.filesByLanguage.put(key, value);
            return this;
        }

        public WorkspaceStarterFilesResponseV2 build() {
            return new WorkspaceStarterFilesResponseV2(filesByLanguage);
        }
    }
}
