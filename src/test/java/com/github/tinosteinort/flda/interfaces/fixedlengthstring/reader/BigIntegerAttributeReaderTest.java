package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BigIntegerAttributeReaderTest {

    private final BigIntegerAttributeReader reader = new BigIntegerAttributeReader();

    @Test public void bigValue() {
        final FixedLengthString data = new FixedLengthString("999999999999999999999999999999999999999999999999");
        final FixedLengthStringAttribute<BigInteger> attribute = new FixedLengthStringAttribute<>(BigInteger.class, 0, 48);

        assertEquals(new BigInteger("999999999999999999999999999999999999999999999999"), reader.read(data, attribute));
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<BigInteger> attribute = new FixedLengthStringAttribute<>(BigInteger.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }
}
