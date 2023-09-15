/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.seed.trace.resources.v2.v3.problem.types;

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
@JsonDeserialize(builder = GetBasicSolutionFileResponse.Builder.class)
public final class GetBasicSolutionFileResponse {
    private final Map<Language, FileInfoV2> solutionFileByLanguage;

    private GetBasicSolutionFileResponse(Map<Language, FileInfoV2> solutionFileByLanguage) {
        this.solutionFileByLanguage = solutionFileByLanguage;
    }

    @JsonProperty("solutionFileByLanguage")
    public Map<Language, FileInfoV2> getSolutionFileByLanguage() {
        return solutionFileByLanguage;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GetBasicSolutionFileResponse && equalTo((GetBasicSolutionFileResponse) other);
    }

    private boolean equalTo(GetBasicSolutionFileResponse other) {
        return solutionFileByLanguage.equals(other.solutionFileByLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.solutionFileByLanguage);
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
        private Map<Language, FileInfoV2> solutionFileByLanguage = new LinkedHashMap<>();

        private Builder() {}

        public Builder from(GetBasicSolutionFileResponse other) {
            solutionFileByLanguage(other.getSolutionFileByLanguage());
            return this;
        }

        @JsonSetter(value = "solutionFileByLanguage", nulls = Nulls.SKIP)
        public Builder solutionFileByLanguage(Map<Language, FileInfoV2> solutionFileByLanguage) {
            this.solutionFileByLanguage.clear();
            this.solutionFileByLanguage.putAll(solutionFileByLanguage);
            return this;
        }

        public Builder putAllSolutionFileByLanguage(Map<Language, FileInfoV2> solutionFileByLanguage) {
            this.solutionFileByLanguage.putAll(solutionFileByLanguage);
            return this;
        }

        public Builder solutionFileByLanguage(Language key, FileInfoV2 value) {
            this.solutionFileByLanguage.put(key, value);
            return this;
        }

        public GetBasicSolutionFileResponse build() {
            return new GetBasicSolutionFileResponse(solutionFileByLanguage);
        }
    }
}
