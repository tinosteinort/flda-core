package com.github.tinosteinort.flda.interfaces.stringlist;

import com.github.tinosteinort.flda.accessor.RecordValidator;

import java.util.List;

/**
 * A class that checks if a {@code List<String>} has the expected size.
 */
public class SizeValidator implements RecordValidator<List<String>> {

    private final int size;

    public SizeValidator(final int size) {
        this.size = size;
    }

    /**
     * Performs the validation.
     *
     * @param data the record to validate.
     * @throws IllegalArgumentException if the {@code List<String>} has an unexpected size.
     */
    @Override public void validate(final List<String> data) {
        if (data == null) {
            throw new NullPointerException("null not allowed");
        }
        if (data.size() != size) {
            throw new IllegalArgumentException("Invalid size: " + data.size() + ", expected: " + size);
        }
    }
}
