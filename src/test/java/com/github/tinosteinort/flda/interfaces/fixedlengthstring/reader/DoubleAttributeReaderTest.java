package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DoubleAttributeReaderTest {

    private final DoubleAttributeReader reader = new DoubleAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("4.9e-324");
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 8);

        assertEquals((Double) 0x0.0000000000001P-1022, reader.read(data, attribute));
    }

    @Test public void minNormalValue() {
        final FixedLengthString data = new FixedLengthString("2.2250738585072014E-308");
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 23);

        assertEquals((Double) 0x1.0p-1022, reader.read(data, attribute));
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("1.7976931348623157e+308");
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 23);

        assertEquals((Double) 0x1.fffffffffffffP+1023, reader.read(data, attribute));
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = NumberFormatException.class)
    public void valueOutOfBounds() {
        final FixedLengthString data = new FixedLengthString("1.7976931348623157e+309");
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 19);

        reader.read(data, attribute);
    }
}
