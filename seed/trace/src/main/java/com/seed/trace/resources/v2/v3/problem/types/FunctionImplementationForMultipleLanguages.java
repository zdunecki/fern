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
@JsonDeserialize(builder = FunctionImplementationForMultipleLanguages.Builder.class)
public final class FunctionImplementationForMultipleLanguages {
    private final Map<Language, FunctionImplementation> codeByLanguage;

    private FunctionImplementationForMultipleLanguages(Map<Language, FunctionImplementation> codeByLanguage) {
        this.codeByLanguage = codeByLanguage;
    }

    @JsonProperty("codeByLanguage")
    public Map<Language, FunctionImplementation> getCodeByLanguage() {
        return codeByLanguage;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof FunctionImplementationForMultipleLanguages
                && equalTo((FunctionImplementationForMultipleLanguages) other);
    }

    private boolean equalTo(FunctionImplementationForMultipleLanguages other) {
        return codeByLanguage.equals(other.codeByLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codeByLanguage);
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
        private Map<Language, FunctionImplementation> codeByLanguage = new LinkedHashMap<>();

        private Builder() {}

        public Builder from(FunctionImplementationForMultipleLanguages other) {
            codeByLanguage(other.getCodeByLanguage());
            return this;
        }

        @JsonSetter(value = "codeByLanguage", nulls = Nulls.SKIP)
        public Builder codeByLanguage(Map<Language, FunctionImplementation> codeByLanguage) {
            this.codeByLanguage.clear();
            this.codeByLanguage.putAll(codeByLanguage);
            return this;
        }

        public Builder putAllCodeByLanguage(Map<Language, FunctionImplementation> codeByLanguage) {
            this.codeByLanguage.putAll(codeByLanguage);
            return this;
        }

        public Builder codeByLanguage(Language key, FunctionImplementation value) {
            this.codeByLanguage.put(key, value);
            return this;
        }

        public FunctionImplementationForMultipleLanguages build() {
            return new FunctionImplementationForMultipleLanguages(codeByLanguage);
        }
    }
}
