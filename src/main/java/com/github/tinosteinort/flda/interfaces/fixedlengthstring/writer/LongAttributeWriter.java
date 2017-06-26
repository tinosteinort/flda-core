package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class LongAttributeWriter extends NumberAttributeWriter<Long> {

    public LongAttributeWriter() {
    }

    public LongAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Long value) {
        return String.valueOf(value);
    }
}
