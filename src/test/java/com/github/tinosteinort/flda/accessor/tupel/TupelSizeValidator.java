package com.github.tinosteinort.flda.accessor.tupel;

import com.github.tinosteinort.flda.accessor.RecordValidator;

public class TupelSizeValidator implements RecordValidator<Tupel> {

    private final int expectedSize;

    public TupelSizeValidator(final int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override public void validate(final Tupel record) {
        if (record == null) {
            throw new NullPointerException("null not allowed");
        }
        if (record.size() != expectedSize) {
            throw new IllegalArgumentException("Invalid length: " + record.size() + ", expected: " + expectedSize);
        }
    }
}
