package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

public class IntegerAttributeReader extends NumberAttributeReader<Integer> {

    @Override protected Integer nullSafeConvertToNumber(final String value) {
        return Integer.valueOf(value);
    }
}
