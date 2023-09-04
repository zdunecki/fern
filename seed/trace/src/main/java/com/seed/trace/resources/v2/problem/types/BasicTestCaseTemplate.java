package com.seed.trace.resources.v2.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = BasicTestCaseTemplate.Builder.class)
public final class BasicTestCaseTemplate {
    private final String templateId;

    private final String name;

    private final TestCaseImplementationDescription description;

    private final String expectedValueParameterId;

    private BasicTestCaseTemplate(
            String templateId,
            String name,
            TestCaseImplementationDescription description,
            String expectedValueParameterId) {
        this.templateId = templateId;
        this.name = name;
        this.description = description;
        this.expectedValueParameterId = expectedValueParameterId;
    }

    @JsonProperty("templateId")
    public String getTemplateId() {
        return templateId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("description")
    public TestCaseImplementationDescription getDescription() {
        return description;
    }

    @JsonProperty("expectedValueParameterId")
    public String getExpectedValueParameterId() {
        return expectedValueParameterId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BasicTestCaseTemplate && equalTo((BasicTestCaseTemplate) other);
    }

    private boolean equalTo(BasicTestCaseTemplate other) {
        return templateId.equals(other.templateId)
                && name.equals(other.name)
                && description.equals(other.description)
                && expectedValueParameterId.equals(other.expectedValueParameterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.templateId, this.name, this.description, this.expectedValueParameterId);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TemplateIdStage builder() {
        return new Builder();
    }

    public interface TemplateIdStage {
        NameStage templateId(String templateId);

        Builder from(BasicTestCaseTemplate other);
    }

    public interface NameStage {
        DescriptionStage name(String name);
    }

    public interface DescriptionStage {
        ExpectedValueParameterIdStage description(TestCaseImplementationDescription description);
    }

    public interface ExpectedValueParameterIdStage {
        _FinalStage expectedValueParameterId(String expectedValueParameterId);
    }

    public interface _FinalStage {
        BasicTestCaseTemplate build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements TemplateIdStage, NameStage, DescriptionStage, ExpectedValueParameterIdStage, _FinalStage {
        private String templateId;

        private String name;

        private TestCaseImplementationDescription description;

        private String expectedValueParameterId;

        private Builder() {}

        @Override
        public Builder from(BasicTestCaseTemplate other) {
            templateId(other.getTemplateId());
            name(other.getName());
            description(other.getDescription());
            expectedValueParameterId(other.getExpectedValueParameterId());
            return this;
        }

        @Override
        @JsonSetter("templateId")
        public NameStage templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        @Override
        @JsonSetter("name")
        public DescriptionStage name(String name) {
            this.name = name;
            return this;
        }

        @Override
        @JsonSetter("description")
        public ExpectedValueParameterIdStage description(TestCaseImplementationDescription description) {
            this.description = description;
            return this;
        }

        @Override
        @JsonSetter("expectedValueParameterId")
        public _FinalStage expectedValueParameterId(String expectedValueParameterId) {
            this.expectedValueParameterId = expectedValueParameterId;
            return this;
        }

        @Override
        public BasicTestCaseTemplate build() {
            return new BasicTestCaseTemplate(templateId, name, description, expectedValueParameterId);
        }
    }
}
