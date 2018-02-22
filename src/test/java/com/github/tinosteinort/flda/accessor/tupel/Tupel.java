package com.github.tinosteinort.flda.accessor.tupel;

import java.util.Arrays;

/**
 * Some type of a record for the unit tests
 */
public class Tupel {

    private final Object[] data;

    public Tupel(final int size) {
        this.data = new Object[size];
    }

    public void set(final int index, final Object object) {
        data[index] = object;
    }

    public Object get(final int index) {
        return data[index];
    }

    public int size() {
        return data.length;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tupel tupel = (Tupel) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(data, tupel.data);
    }

    @Override public int hashCode() {
        return Arrays.hashCode(data);
    }
}
