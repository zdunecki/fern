package com.seed.trace.resources.problem.types;

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
@JsonDeserialize(builder = ProblemFiles.Builder.class)
public final class ProblemFiles {
    private final FileInfo solutionFile;

    private final List<FileInfo> readOnlyFiles;

    private ProblemFiles(FileInfo solutionFile, List<FileInfo> readOnlyFiles) {
        this.solutionFile = solutionFile;
        this.readOnlyFiles = readOnlyFiles;
    }

    @JsonProperty("solutionFile")
    public FileInfo getSolutionFile() {
        return solutionFile;
    }

    @JsonProperty("readOnlyFiles")
    public List<FileInfo> getReadOnlyFiles() {
        return readOnlyFiles;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ProblemFiles && equalTo((ProblemFiles) other);
    }

    private boolean equalTo(ProblemFiles other) {
        return solutionFile.equals(other.solutionFile) && readOnlyFiles.equals(other.readOnlyFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.solutionFile, this.readOnlyFiles);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SolutionFileStage builder() {
        return new Builder();
    }

    public interface SolutionFileStage {
        _FinalStage solutionFile(FileInfo solutionFile);

        Builder from(ProblemFiles other);
    }

    public interface _FinalStage {
        ProblemFiles build();

        _FinalStage readOnlyFiles(List<FileInfo> readOnlyFiles);

        _FinalStage addReadOnlyFiles(FileInfo readOnlyFiles);

        _FinalStage addAllReadOnlyFiles(List<FileInfo> readOnlyFiles);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SolutionFileStage, _FinalStage {
        private FileInfo solutionFile;

        private List<FileInfo> readOnlyFiles = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(ProblemFiles other) {
            solutionFile(other.getSolutionFile());
            readOnlyFiles(other.getReadOnlyFiles());
            return this;
        }

        @Override
        @JsonSetter("solutionFile")
        public _FinalStage solutionFile(FileInfo solutionFile) {
            this.solutionFile = solutionFile;
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
        public ProblemFiles build() {
            return new ProblemFiles(solutionFile, readOnlyFiles);
        }
    }
}
