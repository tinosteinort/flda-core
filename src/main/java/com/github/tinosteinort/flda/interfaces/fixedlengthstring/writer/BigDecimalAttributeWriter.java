package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import java.math.BigDecimal;

public class BigDecimalAttributeWriter extends NumberAttributeWriter<BigDecimal> {

    public BigDecimalAttributeWriter() {
    }

    public BigDecimalAttributeWriter(final char filler) {
        super(filler);
    }

    @Override protected String nullSafeConvertToString(final BigDecimal value) {
        return String.valueOf(value);
    }
}
