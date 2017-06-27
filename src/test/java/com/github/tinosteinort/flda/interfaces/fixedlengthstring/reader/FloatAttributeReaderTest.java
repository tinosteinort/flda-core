package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FloatAttributeReaderTest {

    private final FloatAttributeReader reader = new FloatAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("1.4e-45f");
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 8);

        assertEquals((Float) 0x0.000002P-126f, reader.read(data, attribute));
    }

    @Test public void minNormalValue() {
        final FixedLengthString data = new FixedLengthString("1.17549435E-38f");
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 15);

        assertEquals((Float) 0x1.0p-126f, reader.read(data, attribute));
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("3.4028235e+38f");
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 14);

        assertEquals((Float) 0x1.fffffeP+127f, reader.read(data, attribute));
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }
}
