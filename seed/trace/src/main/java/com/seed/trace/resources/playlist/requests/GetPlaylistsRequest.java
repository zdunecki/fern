package com.seed.trace.resources.playlist.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.seed.trace.core.ObjectMappers;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GetPlaylistsRequest.Builder.class)
public final class GetPlaylistsRequest {
    private final Optional<Integer> limit;

    private final String otherField;

    private final String multiLineDocs;

    private final Optional<String> optionalMultipleField;

    private final String multipleField;

    private GetPlaylistsRequest(
            Optional<Integer> limit,
            String otherField,
            String multiLineDocs,
            Optional<String> optionalMultipleField,
            String multipleField) {
        this.limit = limit;
        this.otherField = otherField;
        this.multiLineDocs = multiLineDocs;
        this.optionalMultipleField = optionalMultipleField;
        this.multipleField = multipleField;
    }

    @JsonProperty("limit")
    public Optional<Integer> getLimit() {
        return limit;
    }

    /**
     * @return i'm another field
     */
    @JsonProperty("otherField")
    public String getOtherField() {
        return otherField;
    }

    /**
     * @return I'm a multiline
     * description
     */
    @JsonProperty("multiLineDocs")
    public String getMultiLineDocs() {
        return multiLineDocs;
    }

    @JsonProperty("optionalMultipleField")
    public Optional<String> getOptionalMultipleField() {
        return optionalMultipleField;
    }

    @JsonProperty("multipleField")
    public String getMultipleField() {
        return multipleField;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GetPlaylistsRequest && equalTo((GetPlaylistsRequest) other);
    }

    private boolean equalTo(GetPlaylistsRequest other) {
        return limit.equals(other.limit)
                && otherField.equals(other.otherField)
                && multiLineDocs.equals(other.multiLineDocs)
                && optionalMultipleField.equals(other.optionalMultipleField)
                && multipleField.equals(other.multipleField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.limit, this.otherField, this.multiLineDocs, this.optionalMultipleField, this.multipleField);
    }

    @Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static OtherFieldStage builder() {
        return new Builder();
    }

    public interface OtherFieldStage {
        MultiLineDocsStage otherField(String otherField);

        Builder from(GetPlaylistsRequest other);
    }

    public interface MultiLineDocsStage {
        MultipleFieldStage multiLineDocs(String multiLineDocs);
    }

    public interface MultipleFieldStage {
        _FinalStage multipleField(String multipleField);
    }

    public interface _FinalStage {
        GetPlaylistsRequest build();

        _FinalStage limit(Optional<Integer> limit);

        _FinalStage limit(Integer limit);

        _FinalStage optionalMultipleField(Optional<String> optionalMultipleField);

        _FinalStage optionalMultipleField(String optionalMultipleField);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements OtherFieldStage, MultiLineDocsStage, MultipleFieldStage, _FinalStage {
        private String otherField;

        private String multiLineDocs;

        private String multipleField;

        private Optional<String> optionalMultipleField = Optional.empty();

        private Optional<Integer> limit = Optional.empty();

        private Builder() {}

        @Override
        public Builder from(GetPlaylistsRequest other) {
            limit(other.getLimit());
            otherField(other.getOtherField());
            multiLineDocs(other.getMultiLineDocs());
            optionalMultipleField(other.getOptionalMultipleField());
            multipleField(other.getMultipleField());
            return this;
        }

        /**
         * <p>i'm another field</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        @JsonSetter("otherField")
        public MultiLineDocsStage otherField(String otherField) {
            this.otherField = otherField;
            return this;
        }

        /**
         * <p>I'm a multiline
         * description</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @Override
        @JsonSetter("multiLineDocs")
        public MultipleFieldStage multiLineDocs(String multiLineDocs) {
            this.multiLineDocs = multiLineDocs;
            return this;
        }

        @Override
        @JsonSetter("multipleField")
        public _FinalStage multipleField(String multipleField) {
            this.multipleField = multipleField;
            return this;
        }

        @Override
        public _FinalStage optionalMultipleField(String optionalMultipleField) {
            this.optionalMultipleField = Optional.of(optionalMultipleField);
            return this;
        }

        @Override
        @JsonSetter(value = "optionalMultipleField", nulls = Nulls.SKIP)
        public _FinalStage optionalMultipleField(Optional<String> optionalMultipleField) {
            this.optionalMultipleField = optionalMultipleField;
            return this;
        }

        @Override
        public _FinalStage limit(Integer limit) {
            this.limit = Optional.of(limit);
            return this;
        }

        @Override
        @JsonSetter(value = "limit", nulls = Nulls.SKIP)
        public _FinalStage limit(Optional<Integer> limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public GetPlaylistsRequest build() {
            return new GetPlaylistsRequest(limit, otherField, multiLineDocs, optionalMultipleField, multipleField);
        }
    }
}
