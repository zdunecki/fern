package com.seed.trace.resources.submission.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = SubmissionFileInfo.Builder.class)
public final class SubmissionFileInfo {
    private final String directory;

    private final String filename;

    private final String contents;

    private SubmissionFileInfo(String directory, String filename, String contents) {
        this.directory = directory;
        this.filename = filename;
        this.contents = contents;
    }

    @JsonProperty("directory")
    public String getDirectory() {
        return directory;
    }

    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    @JsonProperty("contents")
    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof SubmissionFileInfo && equalTo((SubmissionFileInfo) other);
    }

    private boolean equalTo(SubmissionFileInfo other) {
        return directory.equals(other.directory) && filename.equals(other.filename) && contents.equals(other.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.directory, this.filename, this.contents);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static DirectoryStage builder() {
        return new Builder();
    }

    public interface DirectoryStage {
        FilenameStage directory(String directory);

        Builder from(SubmissionFileInfo other);
    }

    public interface FilenameStage {
        ContentsStage filename(String filename);
    }

    public interface ContentsStage {
        _FinalStage contents(String contents);
    }

    public interface _FinalStage {
        SubmissionFileInfo build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements DirectoryStage, FilenameStage, ContentsStage, _FinalStage {
        private String directory;

        private String filename;

        private String contents;

        private Builder() {}

        @Override
        public Builder from(SubmissionFileInfo other) {
            directory(other.getDirectory());
            filename(other.getFilename());
            contents(other.getContents());
            return this;
        }

        @Override
        @JsonSetter("directory")
        public FilenameStage directory(String directory) {
            this.directory = directory;
            return this;
        }

        @Override
        @JsonSetter("filename")
        public ContentsStage filename(String filename) {
            this.filename = filename;
            return this;
        }

        @Override
        @JsonSetter("contents")
        public _FinalStage contents(String contents) {
            this.contents = contents;
            return this;
        }

        @Override
        public SubmissionFileInfo build() {
            return new SubmissionFileInfo(directory, filename, contents);
        }
    }
}
