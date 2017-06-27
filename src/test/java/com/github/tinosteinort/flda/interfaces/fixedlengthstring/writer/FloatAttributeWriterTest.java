package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FloatAttributeWriterTest {

    private final FloatAttributeWriter writer = new FloatAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 7);
        writer.write(data, attribute, 0x0.000002P-126f);
        assertEquals("1.4E-45", data.getString());
    }

    @Test public void writeMinNormal() {
        final FixedLengthString data = new FixedLengthString(14, ' ');
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 14);
        writer.write(data, attribute, 0x1.0p-126f);
        assertEquals("1.17549435E-38", data.getString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(12, ' ');
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 12);
        writer.write(data, attribute, 0x1.fffffeP+127f);
        assertEquals("3.4028235E38", data.getString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(14, ' ');
        final FixedLengthStringAttribute<Float> attribute = new FixedLengthStringAttribute<>(Float.class, 0, 14);
        writer.write(data, attribute, null);
        assertEquals("              ", data.getString());
    }
}
