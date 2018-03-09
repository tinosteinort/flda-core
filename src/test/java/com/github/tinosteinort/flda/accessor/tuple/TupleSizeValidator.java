package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.RecordValidator;

public class TupleSizeValidator implements RecordValidator<Tuple> {

    private final int expectedSize;

    public TupleSizeValidator(final int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override public void validate(final Tuple record) {
        if (record == null) {
            throw new NullPointerException("null not allowed");
        }
        if (record.size() != expectedSize) {
            throw new IllegalArgumentException("Invalid length: " + record.size() + ", expected: " + expectedSize);
        }
    }
}
