package com.seed.trace.resources.v2.v3.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.VariableType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = DefaultProvidedFile.Builder.class)
public final class DefaultProvidedFile {
    private final FileInfoV2 file;

    private final List<VariableType> relatedTypes;

    private DefaultProvidedFile(FileInfoV2 file, List<VariableType> relatedTypes) {
        this.file = file;
        this.relatedTypes = relatedTypes;
    }

    @JsonProperty("file")
    public FileInfoV2 getFile() {
        return file;
    }

    @JsonProperty("relatedTypes")
    public List<VariableType> getRelatedTypes() {
        return relatedTypes;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof DefaultProvidedFile && equalTo((DefaultProvidedFile) other);
    }

    private boolean equalTo(DefaultProvidedFile other) {
        return file.equals(other.file) && relatedTypes.equals(other.relatedTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.file, this.relatedTypes);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static FileStage builder() {
        return new Builder();
    }

    public interface FileStage {
        _FinalStage file(FileInfoV2 file);

        Builder from(DefaultProvidedFile other);
    }

    public interface _FinalStage {
        DefaultProvidedFile build();

        _FinalStage relatedTypes(List<VariableType> relatedTypes);

        _FinalStage addRelatedTypes(VariableType relatedTypes);

        _FinalStage addAllRelatedTypes(List<VariableType> relatedTypes);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements FileStage, _FinalStage {
        private FileInfoV2 file;

        private List<VariableType> relatedTypes = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(DefaultProvidedFile other) {
            file(other.getFile());
            relatedTypes(other.getRelatedTypes());
            return this;
        }

        @Override
        @JsonSetter("file")
        public _FinalStage file(FileInfoV2 file) {
            this.file = file;
            return this;
        }

        @Override
        public _FinalStage addAllRelatedTypes(List<VariableType> relatedTypes) {
            this.relatedTypes.addAll(relatedTypes);
            return this;
        }

        @Override
        public _FinalStage addRelatedTypes(VariableType relatedTypes) {
            this.relatedTypes.add(relatedTypes);
            return this;
        }

        @Override
        @JsonSetter(value = "relatedTypes", nulls = Nulls.SKIP)
        public _FinalStage relatedTypes(List<VariableType> relatedTypes) {
            this.relatedTypes.clear();
            this.relatedTypes.addAll(relatedTypes);
            return this;
        }

        @Override
        public DefaultProvidedFile build() {
            return new DefaultProvidedFile(file, relatedTypes);
        }
    }
}
