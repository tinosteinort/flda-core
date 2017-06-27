package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LongAttributeReaderTest {

    private final LongAttributeReader reader = new LongAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("-9223372036854775808");
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 20);

        assertEquals((Long) 0x8000000000000000L, reader.read(data, attribute));
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("9223372036854775807");
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 19);

        assertEquals((Long) 0x7fffffffffffffffL, reader.read(data, attribute));
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = NumberFormatException.class)
    public void valueOutOfBounds() {
        final FixedLengthString data = new FixedLengthString("9223372036854775808");
        final FixedLengthStringAttribute<Long> attribute = new FixedLengthStringAttribute<>(Long.class, 0, 19);

        reader.read(data, attribute);
    }
}
