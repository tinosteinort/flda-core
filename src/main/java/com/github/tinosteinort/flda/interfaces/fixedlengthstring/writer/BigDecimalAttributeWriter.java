package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import java.math.BigDecimal;

public class BigDecimalAttributeWriter extends NumberAttributeWriter<BigDecimal> {

    public BigDecimalAttributeWriter() {
    }

    public BigDecimalAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        super(alignment, filler);
    }

    @Override protected String nullSafeConvertToString(final BigDecimal value) {
        return String.valueOf(value);
    }
}
