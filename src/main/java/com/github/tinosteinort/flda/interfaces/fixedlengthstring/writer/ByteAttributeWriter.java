package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class ByteAttributeWriter extends NumberAttributeWriter<Byte> {

    public ByteAttributeWriter() {
    }

    public ByteAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final Byte value) {
        return String.valueOf(value);
    }
}
