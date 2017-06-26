package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class FloatAttributeWriter extends NumberAttributeWriter<Float> {

    public FloatAttributeWriter() {
    }

    public FloatAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Float value) {
        return String.valueOf(value);
    }
}
