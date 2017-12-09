package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ByteAttributeWriterTest {

    private final ByteAttributeWriter writer = new ByteAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(4, ' ');
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 4);
        writer.write(data, attribute, (byte) -128);
        assertEquals("-128", data.toString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(3, ' ');
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 3);
        writer.write(data, attribute, (byte) 127);
        assertEquals("127", data.toString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(3, ' ');
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 3);
        writer.write(data, attribute, null);
        assertEquals("   ", data.toString());
    }
}
