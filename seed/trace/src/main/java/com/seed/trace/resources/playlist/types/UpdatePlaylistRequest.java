package com.seed.trace.resources.playlist.types;

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
@JsonDeserialize(builder = UpdatePlaylistRequest.Builder.class)
public final class UpdatePlaylistRequest {
    private final String name;

    private final List<String> problems;

    private UpdatePlaylistRequest(String name, List<String> problems) {
        this.name = name;
        this.problems = problems;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @return The problems that make up the playlist.
     */
    @JsonProperty("problems")
    public List<String> getProblems() {
        return problems;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof UpdatePlaylistRequest && equalTo((UpdatePlaylistRequest) other);
    }

    private boolean equalTo(UpdatePlaylistRequest other) {
        return name.equals(other.name) && problems.equals(other.problems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.problems);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static NameStage builder() {
        return new Builder();
    }

    public interface NameStage {
        _FinalStage name(String name);

        Builder from(UpdatePlaylistRequest other);
    }

    public interface _FinalStage {
        UpdatePlaylistRequest build();

        _FinalStage problems(List<String> problems);

        _FinalStage addProblems(String problems);

        _FinalStage addAllProblems(List<String> problems);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements NameStage, _FinalStage {
        private String name;

        private List<String> problems = new ArrayList<>();

        private Builder() {}

        @Override
        public Builder from(UpdatePlaylistRequest other) {
            name(other.getName());
            problems(other.getProblems());
            return this;
        }

        @Override
        @JsonSetter("name")
        public _FinalStage name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <p>The problems that make up the playlist.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        public _FinalStage addAllProblems(List<String> problems) {
            this.problems.addAll(problems);
            return this;
        }

        /**
         * <p>The problems that make up the playlist.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        public _FinalStage addProblems(String problems) {
            this.problems.add(problems);
            return this;
        }

        @Override
        @JsonSetter(value = "problems", nulls = Nulls.SKIP)
        public _FinalStage problems(List<String> problems) {
            this.problems.clear();
            this.problems.addAll(problems);
            return this;
        }

        @Override
        public UpdatePlaylistRequest build() {
            return new UpdatePlaylistRequest(name, problems);
        }
    }
}
