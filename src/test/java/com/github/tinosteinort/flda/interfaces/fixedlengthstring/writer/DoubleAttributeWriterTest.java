package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleAttributeWriterTest {

    private final DoubleAttributeWriter writer = new DoubleAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(8, ' ');
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 8);
        writer.write(data, attribute, 0x0.0000000000001P-1022);
        assertEquals("4.9E-324", data.getString());
    }

    @Test public void writeMinNormal() {
        final FixedLengthString data = new FixedLengthString(23, ' ');
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 23);
        writer.write(data, attribute, 0x1.0p-1022);
        assertEquals("2.2250738585072014E-308", data.getString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(22, ' ');
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 22);
        writer.write(data, attribute, 0x1.fffffffffffffP+1023);
        assertEquals("1.7976931348623157E308", data.getString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(23, ' ');
        final FixedLengthStringAttribute<Double> attribute = new FixedLengthStringAttribute<>(Double.class, 0, 23);
        writer.write(data, attribute, null);
        assertEquals("                       ", data.getString());
    }
}
