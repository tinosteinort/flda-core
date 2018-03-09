package com.github.tinosteinort.flda.accessor.tuple;

import java.util.function.Supplier;

public class TupleFactory implements Supplier<Tuple> {

    private final int size;

    public TupleFactory(final int size) {
        this.size = size;
    }

    @Override public Tuple get() {
        return new Tuple(size);
    }
}
