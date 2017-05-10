package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import java.util.Arrays;

public class StringFitter {

    public enum Alignment {
        LEFT,
        RIGHT;
    }

    private final Alignment alignment;

    public StringFitter(final Alignment alignment) {
        this.alignment = alignment;
    }

    public String fit(final String value, final int length) {
        if (value == null) {
            final char[] data = new char[length];
            Arrays.fill(data, ' ');
            return String.valueOf(data);
        }
        else if (value.length() < length) {
            final char[] data = new char[length - value.length()];
            Arrays.fill(data, ' ');
            switch (alignment) {
                case LEFT: return value + String.valueOf(data);
                case RIGHT: return String.valueOf(data) + value;
                default: throw new IllegalArgumentException("Case not allowed: " + alignment);
            }
        }
        else {
            return value.substring(0, length);
        }
    }
}
