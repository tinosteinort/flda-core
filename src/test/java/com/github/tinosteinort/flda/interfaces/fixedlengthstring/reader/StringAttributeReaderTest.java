package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringAttributeReaderTest {

    private final StringAttributeReader reader = new StringAttributeReader();

    @Test public void readValue() {
        final FixedLengthString data = new FixedLengthString("AbcdeF");
        final FixedLengthStringAttribute<String> attribute = new FixedLengthStringAttribute<>(String.class, 0, 6);

        assertEquals("AbcdeF", reader.read(data, attribute));
    }

    @Test public void readNullValue() {
        final FixedLengthString data = new FixedLengthString("      ");
        final FixedLengthStringAttribute<String> attribute = new FixedLengthStringAttribute<>(String.class, 0, 6);

        assertNull(reader.read(data, attribute));
    }
}
