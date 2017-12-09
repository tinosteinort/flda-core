package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class LongAttributeWriter extends NumberAttributeWriter<Long> {

    @Override protected String nullSafeConvertToString(final Long value) {
        return String.valueOf(value);
    }
}
