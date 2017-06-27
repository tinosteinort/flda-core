package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShortAttributeReaderTest {

    private final ShortAttributeReader reader = new ShortAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("-32768");
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 6);

        final Short result = reader.read(data, attribute);
        assertEquals(-32768, (int) result);
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("32767");
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 5);

        final Short result = reader.read(data, attribute);
        assertEquals(32767, (int) result);
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = NumberFormatException.class)
    public void valueOutOfBounds() {
        final FixedLengthString data = new FixedLengthString("32768");
        final FixedLengthStringAttribute<Short> attribute = new FixedLengthStringAttribute<>(Short.class, 0, 5);

        reader.read(data, attribute);
    }
}
