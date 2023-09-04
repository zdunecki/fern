package com.seed.trace.resources.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.Language;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GetDefaultStarterFilesResponse.Builder.class)
public final class GetDefaultStarterFilesResponse {
    private final Map<Language, ProblemFiles> files;

    private GetDefaultStarterFilesResponse(Map<Language, ProblemFiles> files) {
        this.files = files;
    }

    @JsonProperty("files")
    public Map<Language, ProblemFiles> getFiles() {
        return files;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GetDefaultStarterFilesResponse && equalTo((GetDefaultStarterFilesResponse) other);
    }

    private boolean equalTo(GetDefaultStarterFilesResponse other) {
        return files.equals(other.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.files);
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
        private Map<Language, ProblemFiles> files = new LinkedHashMap<>();

        private Builder() {}

        public Builder from(GetDefaultStarterFilesResponse other) {
            files(other.getFiles());
            return this;
        }

        @JsonSetter(value = "files", nulls = Nulls.SKIP)
        public Builder files(Map<Language, ProblemFiles> files) {
            this.files.clear();
            this.files.putAll(files);
            return this;
        }

        public Builder putAllFiles(Map<Language, ProblemFiles> files) {
            this.files.putAll(files);
            return this;
        }

        public Builder files(Language key, ProblemFiles value) {
            this.files.put(key, value);
            return this;
        }

        public GetDefaultStarterFilesResponse build() {
            return new GetDefaultStarterFilesResponse(files);
        }
    }
}
