package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import java.util.Arrays;

public class StringUtils {

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

    public static String readAndTrim(final FixedLengthString data, final int index, final int length,
                                     final char filler) {

        final String value = data.substring(index, index + length);
        final int first = findFirstNonFiller(value, filler);
        final int last = findLastNonFiller(value, filler);

        if (first == -1 && last == -1) {
            return "";
        }
        return value.substring(first, last + 1);
    }

    public static int findFirstNonFiller(final String value, final char filler) {
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != filler) {
                return i;
            }
        }
        return -1;
    }

    public static int findLastNonFiller(final String value, final char filler) {
        for (int i = value.length() - 1; i >= 0; i--) {
            if (value.charAt(i) != filler) {
                return i;
            }
        }
        return -1;
    }
}
