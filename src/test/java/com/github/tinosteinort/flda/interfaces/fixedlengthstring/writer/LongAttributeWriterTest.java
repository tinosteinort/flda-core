package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongAttributeWriterTest {

    private final LongAttributeWriter writer = new LongAttributeWriter();

    @Test public void writeMin() {
        final FixedLengthString data = new FixedLengthString(20, ' ');
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 20);
        writer.write(data, attribute, -9223372036854775808L);
        assertEquals("-9223372036854775808", data.toString());
    }

    @Test public void writeMax() {
        final FixedLengthString data = new FixedLengthString(19, ' ');
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 19);
        writer.write(data, attribute, 9223372036854775807L);
        assertEquals("9223372036854775807", data.toString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(19, ' ');
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 19);
        writer.write(data, attribute, null);
        assertEquals("                   ", data.toString());
    }
}
