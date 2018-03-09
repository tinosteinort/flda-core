package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.Attribute;

/**
 * An attribute description for the tests
 */
public class TupleAttribute<T> implements Attribute<T> {

    private final Class<T> type;
    private final int index;

    public TupleAttribute(final Class<T> type, final int index) {
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
