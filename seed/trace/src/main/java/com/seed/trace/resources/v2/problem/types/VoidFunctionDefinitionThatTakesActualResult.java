package com.seed.trace.resources.v2.problem.types;

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
@JsonDeserialize(builder = VoidFunctionDefinitionThatTakesActualResult.Builder.class)
public final class VoidFunctionDefinitionThatTakesActualResult {
    private final List<Parameter> additionalParameters;

    private final FunctionImplementationForMultipleLanguages code;

    private VoidFunctionDefinitionThatTakesActualResult(
            List<Parameter> additionalParameters, FunctionImplementationForMultipleLanguages code) {
        this.additionalParameters = additionalParameters;
        this.code = code;
    }

    @JsonProperty("additionalParameters")
    public List<Parameter> getAdditionalParameters() {
        return additionalParameters;
    }

    @JsonProperty("code")
    public FunctionImplementationForMultipleLanguages getCode() {
        return code;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof VoidFunctionDefinitionThatTakesActualResult
                && equalTo((VoidFunctionDefinitionThatTakesActualResult) other);
    }

    private boolean equalTo(VoidFunctionDefinitionThatTakesActualResult other) {
        return additionalParameters.equals(other.additionalParameters) && code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.additionalParameters, this.code);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static CodeStage builder() {
        return new Builder();
    }

    public interface CodeStage {
        _FinalStage code(FunctionImplementationForMultipleLanguages code);

        Builder from(VoidFunctionDefinitionThatTakesActualResult other);
    }

    public interface _FinalStage {
        VoidFunctionDefinitionThatTakesActualResult build();

        _FinalStage additionalParameters(List<Parameter> additionalParameters);

        _FinalStage addAdditionalParameters(Parameter additionalParameters);

        _FinalStage addAllAdditionalParameters(List<Parameter> additionalParameters);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements CodeStage, _FinalStage {
        private FunctionImplementationForMultipleLanguages code;

        private List<Parameter> additionalParameters = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(VoidFunctionDefinitionThatTakesActualResult other) {
            additionalParameters(other.getAdditionalParameters());
            code(other.getCode());
            return this;
        }

        @Override
        @JsonSetter("code")
        public _FinalStage code(FunctionImplementationForMultipleLanguages code) {
            this.code = code;
            return this;
        }

        @Override
        public _FinalStage addAllAdditionalParameters(List<Parameter> additionalParameters) {
            this.additionalParameters.addAll(additionalParameters);
            return this;
        }

        @Override
        public _FinalStage addAdditionalParameters(Parameter additionalParameters) {
            this.additionalParameters.add(additionalParameters);
            return this;
        }

        @Override
        @JsonSetter(value = "additionalParameters", nulls = Nulls.SKIP)
        public _FinalStage additionalParameters(List<Parameter> additionalParameters) {
            this.additionalParameters.clear();
            this.additionalParameters.addAll(additionalParameters);
            return this;
        }

        @Override
        public VoidFunctionDefinitionThatTakesActualResult build() {
            return new VoidFunctionDefinitionThatTakesActualResult(additionalParameters, code);
        }
    }
}
