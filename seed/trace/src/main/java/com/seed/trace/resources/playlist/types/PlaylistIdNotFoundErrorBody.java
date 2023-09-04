package com.seed.trace.resources.playlist.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;

public final class PlaylistIdNotFoundErrorBody {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private PlaylistIdNotFoundErrorBody(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static PlaylistIdNotFoundErrorBody playlistId(String value) {
        return new PlaylistIdNotFoundErrorBody(new PlaylistIdValue(value));
    }

    public boolean isPlaylistId() {
        return value instanceof PlaylistIdValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<String> getPlaylistId() {
        if (isPlaylistId()) {
            return Optional.of(((PlaylistIdValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Object> _getUnknown() {
        if (_isUnknown()) {
            return Optional.of(((_UnknownValue) value).value);
        }
        return Optional.empty();
    }

    @JsonValue
    private Value getValue() {
        return this.value;
    }

    public interface Visitor<T> {
        T visitPlaylistId(String playlistId);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes(@JsonSubTypes.Type(PlaylistIdValue.class))
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("playlistId")
    private static final class PlaylistIdValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private PlaylistIdValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitPlaylistId(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof PlaylistIdValue && equalTo((PlaylistIdValue) other);
        }

        private boolean equalTo(PlaylistIdValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "PlaylistIdNotFoundErrorBody{" + "value: " + value + "}";
        }
    }

    private static final class _UnknownValue implements Value {
        private String type;

        @JsonValue
        private Object value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private _UnknownValue(@JsonProperty("value") Object value) {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor._visitUnknown(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
        }

        private boolean equalTo(_UnknownValue other) {
            return type.equals(other.type) && value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.type, this.value);
        }

        @Override
        public String toString() {
            return "PlaylistIdNotFoundErrorBody{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
