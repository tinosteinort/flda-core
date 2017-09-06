package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.RecordValidator;

/**
 * A class that checks if a {@link FixedLengthString} has the expected length.
 */
public class LengthValidator implements RecordValidator<FixedLengthString> {

    private final int length;

    public LengthValidator(final int length) {
        this.length = length;
    }

    /**
     * Performs the validation.
     *
     * @param data the record to validate.
     * @throws IllegalArgumentException if the {@link FixedLengthString} has an unexpected length.
     */
    @Override public void validate(final FixedLengthString data) {
        if (data == null) {
            throw new NullPointerException("null not allowed");
        }
        if (data.length() != length) {
            throw new IllegalArgumentException("Invalid length: " + data.length() + ", expected: " + length);
        }
    }
}
