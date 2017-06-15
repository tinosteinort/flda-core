package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

public class FloatAttributeReader extends NumberAttributeReader<Float> {

    @Override protected Float nullSafeConvertToNumber(final String value) {
        return Float.valueOf(value);
    }
}
