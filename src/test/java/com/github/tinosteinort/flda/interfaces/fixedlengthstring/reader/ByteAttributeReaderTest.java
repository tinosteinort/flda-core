package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ByteAttributeReaderTest {

    private final ByteAttributeReader reader = new ByteAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("-128");
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 4);

        final Byte result = reader.read(data, attribute);
        assertEquals(-128, (int) result);
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("127");
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 3);

        final Byte result = reader.read(data, attribute);
        assertEquals(127, (int) result);
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = NumberFormatException.class)
    public void valueOutOfBounds() {
        final FixedLengthString data = new FixedLengthString("128");
        final FixedLengthStringAttribute<Byte> attribute = new FixedLengthStringAttribute<>(Byte.class, 0, 3);

        reader.read(data, attribute);
    }
}
