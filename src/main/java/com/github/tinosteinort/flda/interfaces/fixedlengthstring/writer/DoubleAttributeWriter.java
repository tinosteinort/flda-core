package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class DoubleAttributeWriter extends NumberAttributeWriter<Double> {

    public DoubleAttributeWriter() {
    }

    public DoubleAttributeWriter(final char filler) {
        super(filler);
    }

    @Override protected String nullSafeConvertToString(final Double value) {
        return String.valueOf(value);
    }
}
