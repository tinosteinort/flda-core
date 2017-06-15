package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntegerAttributeReaderTest {

    private final IntegerAttributeReader reader = new IntegerAttributeReader();

    @Test public void minValue() {
        final FixedLengthString data = new FixedLengthString("-2147483648");
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 11);

        assertEquals((Integer) 0x80000000, reader.read(data, attribute));
        // TODO warum geht das nicht?
        // assertEquals((Integer) -2147483648, reader.read(data, attribute));
    }

    @Test public void maxValue() {
        final FixedLengthString data = new FixedLengthString("2147483647");
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 10);

        assertEquals((Integer) 2147483647, reader.read(data, attribute));
    }

    @Test public void nullValue() {
        final FixedLengthString data = new FixedLengthString("     ");
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 5);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = NumberFormatException.class)
    public void valueOutOfBounds() {
        final FixedLengthString data = new FixedLengthString("2147483648");
        final FixedLengthStringAttribute<Integer> attribute = new FixedLengthStringAttribute<>(Integer.class, 0, 10);

        reader.read(data, attribute);
    }
}
