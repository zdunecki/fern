/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.Language;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = ExecutionSessionResponse.Builder.class)
public final class ExecutionSessionResponse {
    private final String sessionId;

    private final Optional<String> executionSessionUrl;

    private final Language language;

    private final ExecutionSessionStatus status;

    private ExecutionSessionResponse(
            String sessionId, Optional<String> executionSessionUrl, Language language, ExecutionSessionStatus status) {
        this.sessionId = sessionId;
        this.executionSessionUrl = executionSessionUrl;
        this.language = language;
        this.status = status;
    }

    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("executionSessionUrl")
    public Optional<String> getExecutionSessionUrl() {
        return executionSessionUrl;
    }

    @JsonProperty("language")
    public Language getLanguage() {
        return language;
    }

    @JsonProperty("status")
    public ExecutionSessionStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ExecutionSessionResponse && equalTo((ExecutionSessionResponse) other);
    }

    private boolean equalTo(ExecutionSessionResponse other) {
        return sessionId.equals(other.sessionId)
                && executionSessionUrl.equals(other.executionSessionUrl)
                && language.equals(other.language)
                && status.equals(other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sessionId, this.executionSessionUrl, this.language, this.status);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SessionIdStage builder() {
        return new Builder();
    }

    public interface SessionIdStage {
        LanguageStage sessionId(String sessionId);

        Builder from(ExecutionSessionResponse other);
    }

    public interface LanguageStage {
        StatusStage language(Language language);
    }

    public interface StatusStage {
        _FinalStage status(ExecutionSessionStatus status);
    }

    public interface _FinalStage {
        ExecutionSessionResponse build();

        _FinalStage executionSessionUrl(Optional<String> executionSessionUrl);

        _FinalStage executionSessionUrl(String executionSessionUrl);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SessionIdStage, LanguageStage, StatusStage, _FinalStage {
        private String sessionId;

        private Language language;

        private ExecutionSessionStatus status;

        private Optional<String> executionSessionUrl = Optional.empty();

        private Builder() {}

        @Override
        public Builder from(ExecutionSessionResponse other) {
            sessionId(other.getSessionId());
            executionSessionUrl(other.getExecutionSessionUrl());
            language(other.getLanguage());
            status(other.getStatus());
            return this;
        }

        @Override
        @JsonSetter("sessionId")
        public LanguageStage sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        @Override
        @JsonSetter("language")
        public StatusStage language(Language language) {
            this.language = language;
            return this;
        }

        @Override
        @JsonSetter("status")
        public _FinalStage status(ExecutionSessionStatus status) {
            this.status = status;
            return this;
        }

        @Override
        public _FinalStage executionSessionUrl(String executionSessionUrl) {
            this.executionSessionUrl = Optional.of(executionSessionUrl);
            return this;
        }

        @Override
        @JsonSetter(value = "executionSessionUrl", nulls = Nulls.SKIP)
        public _FinalStage executionSessionUrl(Optional<String> executionSessionUrl) {
            this.executionSessionUrl = executionSessionUrl;
            return this;
        }

        @Override
        public ExecutionSessionResponse build() {
            return new ExecutionSessionResponse(sessionId, executionSessionUrl, language, status);
        }
    }
}
