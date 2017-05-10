package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.Attribute;

public class FixedLengthStringAttribute<T> implements Attribute<T> {

    private final Class<T> type;
    private final int index;
    private final int length;

    public FixedLengthStringAttribute(final Class<T> type, final int index, final int length) {
        this.type = type;
        this.index = index;
        this.length = length;
    }

    @Override public Class getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    @Override public String toString() {
        return "FixedLengthStringAttribute{" +
                "type=" + type +
                ", index=" + index +
                ", length=" + length +
                '}';
    }
}
