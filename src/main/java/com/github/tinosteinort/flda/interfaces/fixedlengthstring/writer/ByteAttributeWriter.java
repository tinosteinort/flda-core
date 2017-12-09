package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

public class ByteAttributeWriter extends NumberAttributeWriter<Byte> {

    @Override protected String nullSafeConvertToString(final Byte value) {
        return String.valueOf(value);
    }
}
