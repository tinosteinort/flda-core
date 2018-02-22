package com.github.tinosteinort.flda.accessor.tupel;

import java.util.function.Supplier;

public class TupelFactory implements Supplier<Tupel> {

    private final int size;

    public TupelFactory(final int size) {
        this.size = size;
    }

    @Override public Tupel get() {
        return new Tupel(size);
    }
}
