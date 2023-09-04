package com.seed.trace.resources.problem.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.VariableType;
import com.seed.trace.resources.problem.types.VariableTypeAndName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GetDefaultStarterFilesRequest.Builder.class)
public final class GetDefaultStarterFilesRequest {
    private final List<VariableTypeAndName> inputParams;

    private final VariableType outputType;

    private final String methodName;

    private GetDefaultStarterFilesRequest(
            List<VariableTypeAndName> inputParams, VariableType outputType, String methodName) {
        this.inputParams = inputParams;
        this.outputType = outputType;
        this.methodName = methodName;
    }

    @JsonProperty("inputParams")
    public List<VariableTypeAndName> getInputParams() {
        return inputParams;
    }

    @JsonProperty("outputType")
    public VariableType getOutputType() {
        return outputType;
    }

    /**
     * @return The name of the <code>method</code> that the student has to complete.
     * The method name cannot include the following characters:
     * <ul>
     * <li>Greater Than <code>&gt;</code></li>
     * <li>Less Than `&lt;``</li>
     * <li>Equals <code>=</code></li>
     * <li>Period <code>.</code></li>
     * </ul>
     */
    @JsonProperty("methodName")
    public String getMethodName() {
        return methodName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GetDefaultStarterFilesRequest && equalTo((GetDefaultStarterFilesRequest) other);
    }

    private boolean equalTo(GetDefaultStarterFilesRequest other) {
        return inputParams.equals(other.inputParams)
                && outputType.equals(other.outputType)
                && methodName.equals(other.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.inputParams, this.outputType, this.methodName);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static OutputTypeStage builder() {
        return new Builder();
    }

    public interface OutputTypeStage {
        MethodNameStage outputType(VariableType outputType);

        Builder from(GetDefaultStarterFilesRequest other);
    }

    public interface MethodNameStage {
        _FinalStage methodName(String methodName);
    }

    public interface _FinalStage {
        GetDefaultStarterFilesRequest build();

        _FinalStage inputParams(List<VariableTypeAndName> inputParams);

        _FinalStage addInputParams(VariableTypeAndName inputParams);

        _FinalStage addAllInputParams(List<VariableTypeAndName> inputParams);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements OutputTypeStage, MethodNameStage, _FinalStage {
        private VariableType outputType;

        private String methodName;

        private List<VariableTypeAndName> inputParams = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(GetDefaultStarterFilesRequest other) {
            inputParams(other.getInputParams());
            outputType(other.getOutputType());
            methodName(other.getMethodName());
            return this;
        }

        @Override
        @JsonSetter("outputType")
        public MethodNameStage outputType(VariableType outputType) {
            this.outputType = outputType;
            return this;
        }

        /**
         * <p>The name of the <code>method</code> that the student has to complete.
         * The method name cannot include the following characters:</p>
         * <ul>
         * <li>Greater Than <code>&gt;</code></li>
         * <li>Less Than `&lt;``</li>
         * <li>Equals <code>=</code></li>
         * <li>Period <code>.</code></li>
         * </ul>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        @JsonSetter("methodName")
        public _FinalStage methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        @Override
        public _FinalStage addAllInputParams(List<VariableTypeAndName> inputParams) {
            this.inputParams.addAll(inputParams);
            return this;
        }

        @Override
        public _FinalStage addInputParams(VariableTypeAndName inputParams) {
            this.inputParams.add(inputParams);
            return this;
        }

        @Override
        @JsonSetter(value = "inputParams", nulls = Nulls.SKIP)
        public _FinalStage inputParams(List<VariableTypeAndName> inputParams) {
            this.inputParams.clear();
            this.inputParams.addAll(inputParams);
            return this;
        }

        @Override
        public GetDefaultStarterFilesRequest build() {
            return new GetDefaultStarterFilesRequest(inputParams, outputType, methodName);
        }
    }
}
