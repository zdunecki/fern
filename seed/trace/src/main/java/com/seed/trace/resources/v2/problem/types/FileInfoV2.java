package com.seed.trace.resources.v2.problem.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = FileInfoV2.Builder.class)
public final class FileInfoV2 {
    private final String filename;

    private final String directory;

    private final String contents;

    private final boolean editable;

    private FileInfoV2(String filename, String directory, String contents, boolean editable) {
        this.filename = filename;
        this.directory = directory;
        this.contents = contents;
        this.editable = editable;
    }

    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    @JsonProperty("directory")
    public String getDirectory() {
        return directory;
    }

    @JsonProperty("contents")
    public String getContents() {
        return contents;
    }

    @JsonProperty("editable")
    public boolean getEditable() {
        return editable;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof FileInfoV2 && equalTo((FileInfoV2) other);
    }

    private boolean equalTo(FileInfoV2 other) {
        return filename.equals(other.filename)
                && directory.equals(other.directory)
                && contents.equals(other.contents)
                && editable == other.editable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.filename, this.directory, this.contents, this.editable);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static FilenameStage builder() {
        return new Builder();
    }

    public interface FilenameStage {
        DirectoryStage filename(String filename);

        Builder from(FileInfoV2 other);
    }

    public interface DirectoryStage {
        ContentsStage directory(String directory);
    }

    public interface ContentsStage {
        EditableStage contents(String contents);
    }

    public interface EditableStage {
        _FinalStage editable(boolean editable);
    }

    public interface _FinalStage {
        FileInfoV2 build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements FilenameStage, DirectoryStage, ContentsStage, EditableStage, _FinalStage {
        private String filename;

        private String directory;

        private String contents;

        private boolean editable;

        private Builder() {}

        @Override
        public Builder from(FileInfoV2 other) {
            filename(other.getFilename());
            directory(other.getDirectory());
            contents(other.getContents());
            editable(other.getEditable());
            return this;
        }

        @Override
        @JsonSetter("filename")
        public DirectoryStage filename(String filename) {
            this.filename = filename;
            return this;
        }

        @Override
        @JsonSetter("directory")
        public ContentsStage directory(String directory) {
            this.directory = directory;
            return this;
        }

        @Override
        @JsonSetter("contents")
        public EditableStage contents(String contents) {
            this.contents = contents;
            return this;
        }

        @Override
        @JsonSetter("editable")
        public _FinalStage editable(boolean editable) {
            this.editable = editable;
            return this;
        }

        @Override
        public FileInfoV2 build() {
            return new FileInfoV2(filename, directory, contents, editable);
        }
    }
}
