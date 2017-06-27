package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShortAttributeWriterTest {

    private final ShortAttributeWriter writer = new ShortAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(6, ' ');
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 6);
        writer.write(data, attribute, (short) -32768);
        assertEquals("-32768", data.getString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(5, ' ');
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 5);
        writer.write(data, attribute, (short) 32767);
        assertEquals("32767", data.getString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(5, ' ');
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 5);
        writer.write(data, attribute, null);
        assertEquals("     ", data.getString());
    }
}
