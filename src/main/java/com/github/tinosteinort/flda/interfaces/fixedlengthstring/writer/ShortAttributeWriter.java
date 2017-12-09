package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class ShortAttributeWriter extends NumberAttributeWriter<Short> {

    public ShortAttributeWriter() {
    }

    public ShortAttributeWriter(final char filler) {
        super(filler);
    }

    @Override protected String nullSafeConvertToString(final Short value) {
        return String.valueOf(value);
    }
}
