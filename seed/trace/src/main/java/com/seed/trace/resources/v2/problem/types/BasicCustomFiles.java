package com.seed.trace.resources.v2.problem.types;

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
@JsonDeserialize(builder = BasicCustomFiles.Builder.class)
public final class BasicCustomFiles {
    private final String methodName;

    private final NonVoidFunctionSignature signature;

    private final Map<Language, Files> additionalFiles;

    private final BasicTestCaseTemplate basicTestCaseTemplate;

    private BasicCustomFiles(
            String methodName,
            NonVoidFunctionSignature signature,
            Map<Language, Files> additionalFiles,
            BasicTestCaseTemplate basicTestCaseTemplate) {
        this.methodName = methodName;
        this.signature = signature;
        this.additionalFiles = additionalFiles;
        this.basicTestCaseTemplate = basicTestCaseTemplate;
    }

    @JsonProperty("methodName")
    public String getMethodName() {
        return methodName;
    }

    @JsonProperty("signature")
    public NonVoidFunctionSignature getSignature() {
        return signature;
    }

    @JsonProperty("additionalFiles")
    public Map<Language, Files> getAdditionalFiles() {
        return additionalFiles;
    }

    @JsonProperty("basicTestCaseTemplate")
    public BasicTestCaseTemplate getBasicTestCaseTemplate() {
        return basicTestCaseTemplate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BasicCustomFiles && equalTo((BasicCustomFiles) other);
    }

    private boolean equalTo(BasicCustomFiles other) {
        return methodName.equals(other.methodName)
                && signature.equals(other.signature)
                && additionalFiles.equals(other.additionalFiles)
                && basicTestCaseTemplate.equals(other.basicTestCaseTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.methodName, this.signature, this.additionalFiles, this.basicTestCaseTemplate);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static MethodNameStage builder() {
        return new Builder();
    }

    public interface MethodNameStage {
        SignatureStage methodName(String methodName);

        Builder from(BasicCustomFiles other);
    }

    public interface SignatureStage {
        BasicTestCaseTemplateStage signature(NonVoidFunctionSignature signature);
    }

    public interface BasicTestCaseTemplateStage {
        _FinalStage basicTestCaseTemplate(BasicTestCaseTemplate basicTestCaseTemplate);
    }

    public interface _FinalStage {
        BasicCustomFiles build();

        _FinalStage additionalFiles(Map<Language, Files> additionalFiles);

        _FinalStage putAllAdditionalFiles(Map<Language, Files> additionalFiles);

        _FinalStage additionalFiles(Language key, Files value);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements MethodNameStage, SignatureStage, BasicTestCaseTemplateStage, _FinalStage {
        private String methodName;

        private NonVoidFunctionSignature signature;

        private BasicTestCaseTemplate basicTestCaseTemplate;

        private Map<Language, Files> additionalFiles = new LinkedHashMap<>();

        private Builder() {}

        @Override
        public Builder from(BasicCustomFiles other) {
            methodName(other.getMethodName());
            signature(other.getSignature());
            additionalFiles(other.getAdditionalFiles());
            basicTestCaseTemplate(other.getBasicTestCaseTemplate());
            return this;
        }

        @Override
        @JsonSetter("methodName")
        public SignatureStage methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        @Override
        @JsonSetter("signature")
        public BasicTestCaseTemplateStage signature(NonVoidFunctionSignature signature) {
            this.signature = signature;
            return this;
        }

        @Override
        @JsonSetter("basicTestCaseTemplate")
        public _FinalStage basicTestCaseTemplate(BasicTestCaseTemplate basicTestCaseTemplate) {
            this.basicTestCaseTemplate = basicTestCaseTemplate;
            return this;
        }

        @Override
        public _FinalStage additionalFiles(Language key, Files value) {
            this.additionalFiles.put(key, value);
            return this;
        }

        @Override
        public _FinalStage putAllAdditionalFiles(Map<Language, Files> additionalFiles) {
            this.additionalFiles.putAll(additionalFiles);
            return this;
        }

        @Override
        @JsonSetter(value = "additionalFiles", nulls = Nulls.SKIP)
        public _FinalStage additionalFiles(Map<Language, Files> additionalFiles) {
            this.additionalFiles.clear();
            this.additionalFiles.putAll(additionalFiles);
            return this;
        }

        @Override
        public BasicCustomFiles build() {
            return new BasicCustomFiles(methodName, signature, additionalFiles, basicTestCaseTemplate);
        }
    }
}
