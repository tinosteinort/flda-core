package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import java.util.Arrays;

public class StringFitter {

    public static String fit(final String value, final Alignment alignment, final int length, final char filler) {
        if (value == null) {
            final char[] data = new char[length];
            Arrays.fill(data, filler);
            return String.valueOf(data);
        }
        else if (value.length() < length) {
            final char[] data = new char[length - value.length()];
            Arrays.fill(data, filler);
            switch (alignment) {
                case LEFT: return value + String.valueOf(data);
                case RIGHT: return String.valueOf(data) + value;
                default: throw new IllegalArgumentException("Not a valid Alignment: " + alignment);
            }
        }
        else {
            switch (alignment) {
                case LEFT: return value.substring(0, length);
                case RIGHT: return value.substring(value.length() - length);
                default: throw new IllegalArgumentException("Not a valid Alignment: " + alignment);
            }
        }
    }
}
