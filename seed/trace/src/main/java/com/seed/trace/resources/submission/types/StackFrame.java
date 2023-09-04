package com.seed.trace.resources.submission.types;

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
@JsonDeserialize(builder = StackFrame.Builder.class)
public final class StackFrame {
    private final String methodName;

    private final int lineNumber;

    private final List<Scope> scopes;

    private StackFrame(String methodName, int lineNumber, List<Scope> scopes) {
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.scopes = scopes;
    }

    @JsonProperty("methodName")
    public String getMethodName() {
        return methodName;
    }

    @JsonProperty("lineNumber")
    public int getLineNumber() {
        return lineNumber;
    }

    @JsonProperty("scopes")
    public List<Scope> getScopes() {
        return scopes;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof StackFrame && equalTo((StackFrame) other);
    }

    private boolean equalTo(StackFrame other) {
        return methodName.equals(other.methodName) && lineNumber == other.lineNumber && scopes.equals(other.scopes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.methodName, this.lineNumber, this.scopes);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static MethodNameStage builder() {
        return new Builder();
    }

    public interface MethodNameStage {
        LineNumberStage methodName(String methodName);

        Builder from(StackFrame other);
    }

    public interface LineNumberStage {
        _FinalStage lineNumber(int lineNumber);
    }

    public interface _FinalStage {
        StackFrame build();

        _FinalStage scopes(List<Scope> scopes);

        _FinalStage addScopes(Scope scopes);

        _FinalStage addAllScopes(List<Scope> scopes);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements MethodNameStage, LineNumberStage, _FinalStage {
        private String methodName;

        private int lineNumber;

        private List<Scope> scopes = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(StackFrame other) {
            methodName(other.getMethodName());
            lineNumber(other.getLineNumber());
            scopes(other.getScopes());
            return this;
        }

        @Override
        @JsonSetter("methodName")
        public LineNumberStage methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        @Override
        @JsonSetter("lineNumber")
        public _FinalStage lineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
            return this;
        }

        @Override
        public _FinalStage addAllScopes(List<Scope> scopes) {
            this.scopes.addAll(scopes);
            return this;
        }

        @Override
        public _FinalStage addScopes(Scope scopes) {
            this.scopes.add(scopes);
            return this;
        }

        @Override
        @JsonSetter(value = "scopes", nulls = Nulls.SKIP)
        public _FinalStage scopes(List<Scope> scopes) {
            this.scopes.clear();
            this.scopes.addAll(scopes);
            return this;
        }

        @Override
        public StackFrame build() {
            return new StackFrame(methodName, lineNumber, scopes);
        }
    }
}
