package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import java.util.Arrays;
import java.util.Objects;

public class FixedLengthString {

    private String value;

    public FixedLengthString(final int length, final char initialFiller) {
        final char[] data = new char[length];
        Arrays.fill(data, initialFiller);
        this.value = String.valueOf(data);
    }

    public FixedLengthString(final String value) {
        this.value = Objects.requireNonNull(value, "String must not be NULL");
    }

    public void update(final String value) {
        final String newValue = Objects.requireNonNull(value, "String must not be NULL");
        if (newValue.length() != this.value.length()) {
            throw new IllegalArgumentException("Length has to be equal. Current: " + value.length() + " New: " + newValue.length());
        }
        this.value = newValue;
    }

    public String getString() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FixedLengthString that = (FixedLengthString) o;

        return value.equals(that.value);
    }

    @Override public int hashCode() {
        return value.hashCode();
    }

    @Override public String toString() {
        return "FixedLengthString{" +
                "value='" + value + '\'' +
                '}';
    }
}
