package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import java.math.BigInteger;

public class BigIntegerAttributeWriter extends NumberAttributeWriter<BigInteger> {

    public BigIntegerAttributeWriter() {
    }

    public BigIntegerAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final BigInteger value) {
        return String.valueOf(value);
    }
}
