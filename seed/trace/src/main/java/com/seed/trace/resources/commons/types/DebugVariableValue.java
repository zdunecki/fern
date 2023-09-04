package com.seed.trace.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class DebugVariableValue {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private DebugVariableValue(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static DebugVariableValue integerValue(int value) {
        return new DebugVariableValue(new IntegerValueValue(value));
    }

    public static DebugVariableValue booleanValue(boolean value) {
        return new DebugVariableValue(new BooleanValueValue(value));
    }

    public static DebugVariableValue doubleValue(double value) {
        return new DebugVariableValue(new DoubleValueValue(value));
    }

    public static DebugVariableValue stringValue(String value) {
        return new DebugVariableValue(new StringValueValue(value));
    }

    public static DebugVariableValue charValue(String value) {
        return new DebugVariableValue(new CharValueValue(value));
    }

    public static DebugVariableValue mapValue(DebugMapValue value) {
        return new DebugVariableValue(new MapValueValue(value));
    }

    public static DebugVariableValue listValue(List<DebugVariableValue> value) {
        return new DebugVariableValue(new ListValueValue(value));
    }

    public static DebugVariableValue binaryTreeNodeValue(BinaryTreeNodeAndTreeValue value) {
        return new DebugVariableValue(new BinaryTreeNodeValueValue(value));
    }

    public static DebugVariableValue singlyLinkedListNodeValue(SinglyLinkedListNodeAndListValue value) {
        return new DebugVariableValue(new SinglyLinkedListNodeValueValue(value));
    }

    public static DebugVariableValue doublyLinkedListNodeValue(DoublyLinkedListNodeAndListValue value) {
        return new DebugVariableValue(new DoublyLinkedListNodeValueValue(value));
    }

    public static DebugVariableValue undefinedValue() {
        return new DebugVariableValue(new UndefinedValueValue());
    }

    public static DebugVariableValue nullValue() {
        return new DebugVariableValue(new NullValueValue());
    }

    public static DebugVariableValue genericValue(GenericValue value) {
        return new DebugVariableValue(new GenericValueValue(value));
    }

    public boolean isIntegerValue() {
        return value instanceof IntegerValueValue;
    }

    public boolean isBooleanValue() {
        return value instanceof BooleanValueValue;
    }

    public boolean isDoubleValue() {
        return value instanceof DoubleValueValue;
    }

    public boolean isStringValue() {
        return value instanceof StringValueValue;
    }

    public boolean isCharValue() {
        return value instanceof CharValueValue;
    }

    public boolean isMapValue() {
        return value instanceof MapValueValue;
    }

    public boolean isListValue() {
        return value instanceof ListValueValue;
    }

    public boolean isBinaryTreeNodeValue() {
        return value instanceof BinaryTreeNodeValueValue;
    }

    public boolean isSinglyLinkedListNodeValue() {
        return value instanceof SinglyLinkedListNodeValueValue;
    }

    public boolean isDoublyLinkedListNodeValue() {
        return value instanceof DoublyLinkedListNodeValueValue;
    }

    public boolean isUndefinedValue() {
        return value instanceof UndefinedValueValue;
    }

    public boolean isNullValue() {
        return value instanceof NullValueValue;
    }

    public boolean isGenericValue() {
        return value instanceof GenericValueValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<Integer> getIntegerValue() {
        if (isIntegerValue()) {
            return Optional.of(((IntegerValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Boolean> getBooleanValue() {
        if (isBooleanValue()) {
            return Optional.of(((BooleanValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Double> getDoubleValue() {
        if (isDoubleValue()) {
            return Optional.of(((DoubleValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<String> getStringValue() {
        if (isStringValue()) {
            return Optional.of(((StringValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<String> getCharValue() {
        if (isCharValue()) {
            return Optional.of(((CharValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<DebugMapValue> getMapValue() {
        if (isMapValue()) {
            return Optional.of(((MapValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<List<DebugVariableValue>> getListValue() {
        if (isListValue()) {
            return Optional.of(((ListValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<BinaryTreeNodeAndTreeValue> getBinaryTreeNodeValue() {
        if (isBinaryTreeNodeValue()) {
            return Optional.of(((BinaryTreeNodeValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<SinglyLinkedListNodeAndListValue> getSinglyLinkedListNodeValue() {
        if (isSinglyLinkedListNodeValue()) {
            return Optional.of(((SinglyLinkedListNodeValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<DoublyLinkedListNodeAndListValue> getDoublyLinkedListNodeValue() {
        if (isDoublyLinkedListNodeValue()) {
            return Optional.of(((DoublyLinkedListNodeValueValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<GenericValue> getGenericValue() {
        if (isGenericValue()) {
            return Optional.of(((GenericValueValue) value).value);
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
        T visitIntegerValue(int integerValue);

        T visitBooleanValue(boolean booleanValue);

        T visitDoubleValue(double doubleValue);

        T visitStringValue(String stringValue);

        T visitCharValue(String charValue);

        T visitMapValue(DebugMapValue mapValue);

        T visitListValue(List<DebugVariableValue> listValue);

        T visitBinaryTreeNodeValue(BinaryTreeNodeAndTreeValue binaryTreeNodeValue);

        T visitSinglyLinkedListNodeValue(SinglyLinkedListNodeAndListValue singlyLinkedListNodeValue);

        T visitDoublyLinkedListNodeValue(DoublyLinkedListNodeAndListValue doublyLinkedListNodeValue);

        T visitUndefinedValue();

        T visitNullValue();

        T visitGenericValue(GenericValue genericValue);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(IntegerValueValue.class),
        @JsonSubTypes.Type(BooleanValueValue.class),
        @JsonSubTypes.Type(DoubleValueValue.class),
        @JsonSubTypes.Type(StringValueValue.class),
        @JsonSubTypes.Type(CharValueValue.class),
        @JsonSubTypes.Type(MapValueValue.class),
        @JsonSubTypes.Type(ListValueValue.class),
        @JsonSubTypes.Type(BinaryTreeNodeValueValue.class),
        @JsonSubTypes.Type(SinglyLinkedListNodeValueValue.class),
        @JsonSubTypes.Type(DoublyLinkedListNodeValueValue.class),
        @JsonSubTypes.Type(UndefinedValueValue.class),
        @JsonSubTypes.Type(NullValueValue.class),
        @JsonSubTypes.Type(GenericValueValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("integerValue")
    private static final class IntegerValueValue implements Value {
        @JsonProperty("value")
        private int value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private IntegerValueValue(@JsonProperty("value") int value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitIntegerValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof IntegerValueValue && equalTo((IntegerValueValue) other);
        }

        private boolean equalTo(IntegerValueValue other) {
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("booleanValue")
    private static final class BooleanValueValue implements Value {
        @JsonProperty("value")
        private boolean value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private BooleanValueValue(@JsonProperty("value") boolean value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitBooleanValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof BooleanValueValue && equalTo((BooleanValueValue) other);
        }

        private boolean equalTo(BooleanValueValue other) {
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("doubleValue")
    private static final class DoubleValueValue implements Value {
        @JsonProperty("value")
        private double value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private DoubleValueValue(@JsonProperty("value") double value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitDoubleValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof DoubleValueValue && equalTo((DoubleValueValue) other);
        }

        private boolean equalTo(DoubleValueValue other) {
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("stringValue")
    private static final class StringValueValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private StringValueValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitStringValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof StringValueValue && equalTo((StringValueValue) other);
        }

        private boolean equalTo(StringValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("charValue")
    private static final class CharValueValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private CharValueValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitCharValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof CharValueValue && equalTo((CharValueValue) other);
        }

        private boolean equalTo(CharValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("mapValue")
    private static final class MapValueValue implements Value {
        @JsonUnwrapped
        private DebugMapValue value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private MapValueValue() {}

        private MapValueValue(DebugMapValue value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitMapValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof MapValueValue && equalTo((MapValueValue) other);
        }

        private boolean equalTo(MapValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("listValue")
    private static final class ListValueValue implements Value {
        @JsonProperty("value")
        private List<DebugVariableValue> value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private ListValueValue(@JsonProperty("value") List<DebugVariableValue> value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitListValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof ListValueValue && equalTo((ListValueValue) other);
        }

        private boolean equalTo(ListValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("binaryTreeNodeValue")
    private static final class BinaryTreeNodeValueValue implements Value {
        @JsonUnwrapped
        private BinaryTreeNodeAndTreeValue value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private BinaryTreeNodeValueValue() {}

        private BinaryTreeNodeValueValue(BinaryTreeNodeAndTreeValue value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitBinaryTreeNodeValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof BinaryTreeNodeValueValue && equalTo((BinaryTreeNodeValueValue) other);
        }

        private boolean equalTo(BinaryTreeNodeValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("singlyLinkedListNodeValue")
    private static final class SinglyLinkedListNodeValueValue implements Value {
        @JsonUnwrapped
        private SinglyLinkedListNodeAndListValue value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private SinglyLinkedListNodeValueValue() {}

        private SinglyLinkedListNodeValueValue(SinglyLinkedListNodeAndListValue value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitSinglyLinkedListNodeValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof SinglyLinkedListNodeValueValue && equalTo((SinglyLinkedListNodeValueValue) other);
        }

        private boolean equalTo(SinglyLinkedListNodeValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("doublyLinkedListNodeValue")
    private static final class DoublyLinkedListNodeValueValue implements Value {
        @JsonUnwrapped
        private DoublyLinkedListNodeAndListValue value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private DoublyLinkedListNodeValueValue() {}

        private DoublyLinkedListNodeValueValue(DoublyLinkedListNodeAndListValue value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitDoublyLinkedListNodeValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof DoublyLinkedListNodeValueValue && equalTo((DoublyLinkedListNodeValueValue) other);
        }

        private boolean equalTo(DoublyLinkedListNodeValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("undefinedValue")
    private static final class UndefinedValueValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private UndefinedValueValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitUndefinedValue();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof UndefinedValueValue;
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "}";
        }
    }

    @JsonTypeName("nullValue")
    private static final class NullValueValue implements Value {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private NullValueValue() {}

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitNullValue();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof NullValueValue;
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "}";
        }
    }

    @JsonTypeName("genericValue")
    private static final class GenericValueValue implements Value {
        @JsonUnwrapped
        private GenericValue value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GenericValueValue() {}

        private GenericValueValue(GenericValue value) {
            this.value = value;
        }

        @Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitGenericValue(value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof GenericValueValue && equalTo((GenericValueValue) other);
        }

        private boolean equalTo(GenericValueValue other) {
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public String toString() {
            return "DebugVariableValue{" + "value: " + value + "}";
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
            return "DebugVariableValue{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
