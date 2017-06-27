package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class DoubleAttributeWriter extends NumberAttributeWriter<Double> {

    public DoubleAttributeWriter() {
    }

    public DoubleAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Double value) {
        return String.valueOf(value);
    }
}
