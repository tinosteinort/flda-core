package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import java.util.Arrays;
import java.util.Objects;

public class FixedLengthString {

    private final char[] value;

    public FixedLengthString(final int length, final char initialFiller) {
        this.value = new char[length];
        Arrays.fill(value, initialFiller);
    }

    public FixedLengthString(final String value) {
        this.value = Objects.requireNonNull(value, "String must not be NULL")
                .toCharArray();
    }

    public void update(final int position, final String value) {
        if (value != null && position + value.length() > this.value.length) {
            throw new IllegalArgumentException("Value too long for String");
        }
        final char[] newValue = Objects.requireNonNull(value, "String must not be NULL")
                .toCharArray();
        System.arraycopy(newValue, 0, this.value, position, newValue.length);
    }

    public String getString() {
        return String.valueOf(value);
    }

    @Override public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FixedLengthString that = (FixedLengthString) o;

        return Arrays.equals(value, that.value);
    }

    @Override public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override public String toString() {
        return "FixedLengthString{" +
                "value='" + value + '\'' +
                '}';
    }
}
