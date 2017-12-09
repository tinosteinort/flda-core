package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class ByteAttributeWriter extends NumberAttributeWriter<Byte> {

    public ByteAttributeWriter() {
    }

    public ByteAttributeWriter(final char filler) {
        super(filler);
    }

    @Override protected String nullSafeConvertToString(final Byte value) {
        return String.valueOf(value);
    }
}
