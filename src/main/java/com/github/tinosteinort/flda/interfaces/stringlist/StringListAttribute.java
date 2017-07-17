package com.github.tinosteinort.flda.interfaces.stringlist;

import com.github.tinosteinort.flda.accessor.Attribute;

public class StringListAttribute<T> implements Attribute<T> {

    private final Class<T> type;
    private final int index;

    public StringListAttribute(final Class<T> type, final int index) {
        this.type = type;
        this.index = index;
    }

    @Override public Class<T> getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
