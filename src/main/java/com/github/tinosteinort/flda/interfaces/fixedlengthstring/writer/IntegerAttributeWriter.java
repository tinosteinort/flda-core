package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class IntegerAttributeWriter extends NumberAttributeWriter<Integer> {

    public IntegerAttributeWriter() {
    }

    public IntegerAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Integer value) {
        return String.valueOf(value);
    }
}
