package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class ShortAttributeWriter extends NumberAttributeWriter<Short> {

    public ShortAttributeWriter() {
    }

    public ShortAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Short value) {
        return String.valueOf(value);
    }
}
