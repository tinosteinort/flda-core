package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class BigIntegerAttributeWriterTest {

    private final BigIntegerAttributeWriter writer = new BigIntegerAttributeWriter();

    @Test public void writeBigValue() {
        final FixedLengthString data = new FixedLengthString(48, ' ');
        final FixedLengthStringAttribute<BigInteger> attribute = new FixedLengthStringAttribute<>(BigInteger.class, 0, 48);
        writer.write(data, attribute, new BigInteger("999999999999999999999999999999999999999999999999"));
        assertEquals("999999999999999999999999999999999999999999999999", data.getString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(10, ' ');
        final FixedLengthStringAttribute<BigInteger> attribute = new FixedLengthStringAttribute<>(BigInteger.class, 0, 10);
        writer.write(data, attribute, null);
        assertEquals("          ", data.getString());
    }
}
