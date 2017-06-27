package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumAttributeReaderTest {

    private final EnumAttributeReader<Colour> reader = new EnumAttributeReader<>(Colour.class);

    @Test public void validValue() {
        final FixedLengthString data = new FixedLengthString("GREEN ");
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 6);

        assertEquals(Colour.GREEN, reader.read(data, attribute));
    }

    @Test public void readNullValue() {
        final FixedLengthString data = new FixedLengthString("      ");
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 6);

        assertNull(reader.read(data, attribute));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidValue() {
        final FixedLengthString data = new FixedLengthString("YELLOW ");
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 6);

        reader.read(data, attribute);
    }
}

enum Colour {

    RED,
    GREEN,
    BLUE;
}