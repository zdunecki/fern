package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import com.seed.trace.resources.commons.types.FileInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = WorkspaceFiles.Builder.class)
public final class WorkspaceFiles {
    private final FileInfo mainFile;

    private final List<FileInfo> readOnlyFiles;

    private WorkspaceFiles(FileInfo mainFile, List<FileInfo> readOnlyFiles) {
        this.mainFile = mainFile;
        this.readOnlyFiles = readOnlyFiles;
    }

    @JsonProperty("mainFile")
    public FileInfo getMainFile() {
        return mainFile;
    }

    @JsonProperty("readOnlyFiles")
    public List<FileInfo> getReadOnlyFiles() {
        return readOnlyFiles;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof WorkspaceFiles && equalTo((WorkspaceFiles) other);
    }

    private boolean equalTo(WorkspaceFiles other) {
        return mainFile.equals(other.mainFile) && readOnlyFiles.equals(other.readOnlyFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mainFile, this.readOnlyFiles);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static MainFileStage builder() {
        return new Builder();
    }

    public interface MainFileStage {
        _FinalStage mainFile(FileInfo mainFile);

        Builder from(WorkspaceFiles other);
    }

    public interface _FinalStage {
        WorkspaceFiles build();

        _FinalStage readOnlyFiles(List<FileInfo> readOnlyFiles);

        _FinalStage addReadOnlyFiles(FileInfo readOnlyFiles);

        _FinalStage addAllReadOnlyFiles(List<FileInfo> readOnlyFiles);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements MainFileStage, _FinalStage {
        private FileInfo mainFile;

        private List<FileInfo> readOnlyFiles = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(WorkspaceFiles other) {
            mainFile(other.getMainFile());
            readOnlyFiles(other.getReadOnlyFiles());
            return this;
        }

        @Override
        @JsonSetter("mainFile")
        public _FinalStage mainFile(FileInfo mainFile) {
            this.mainFile = mainFile;
            return this;
        }

        @Override
        public _FinalStage addAllReadOnlyFiles(List<FileInfo> readOnlyFiles) {
            this.readOnlyFiles.addAll(readOnlyFiles);
            return this;
        }

        @Override
        public _FinalStage addReadOnlyFiles(FileInfo readOnlyFiles) {
            this.readOnlyFiles.add(readOnlyFiles);
            return this;
        }

        @Override
        @JsonSetter(value = "readOnlyFiles", nulls = Nulls.SKIP)
        public _FinalStage readOnlyFiles(List<FileInfo> readOnlyFiles) {
            this.readOnlyFiles.clear();
            this.readOnlyFiles.addAll(readOnlyFiles);
            return this;
        }

        @Override
        public WorkspaceFiles build() {
            return new WorkspaceFiles(mainFile, readOnlyFiles);
        }
    }
}
