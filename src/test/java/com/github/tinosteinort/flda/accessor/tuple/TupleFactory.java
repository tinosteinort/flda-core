package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.RecordFactory;

public class TupleFactory implements RecordFactory<Tuple> {

    private final int size;

    public TupleFactory(final int size) {
        this.size = size;
    }

    @Override public Tuple createNewRecord() {
        return new Tuple(size);
    }
}
