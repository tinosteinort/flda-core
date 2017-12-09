package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.Attribute;

public class FixedLengthStringAttribute<T> implements Attribute<T> {

    public static final Alignment DEFAULT_ALIGNMENT = Alignment.LEFT;

    private final Class<T> type;
    private final int index;
    private final int length;
    private final Alignment alignment;

    public FixedLengthStringAttribute(final Class<T> type, final int index, final int length) {
        this(type, index, length, DEFAULT_ALIGNMENT);
    }

    public FixedLengthStringAttribute(final Class<T> type, final int index, final int length, final Alignment alignment) {
        this.type = type;
        this.index = index;
        this.length = length;
        this.alignment = alignment;
    }

    @Override public Class<T> getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    @Override public String toString() {
        return "FixedLengthStringAttribute{" +
                "type=" + type +
                ", index=" + index +
                ", length=" + length +
                '}';
    }
}
