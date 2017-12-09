package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumAttributeWriterTest {

    private final EnumAttributeWriter<Colour> leftWriter = new EnumAttributeWriter<>();
    private final EnumAttributeWriter<Colour> rightWriter = new EnumAttributeWriter<>(' ');

    @Test public void writeValue() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.LEFT);
        leftWriter.write(data, attribute, Colour.RED);
        assertEquals("RED    ", data.getString());
    }

    @Test public void writeNull() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.LEFT);
        leftWriter.write(data, attribute, null);
        assertEquals("       ", data.getString());
    }

    @Test public void writeValueRight() {
        final FixedLengthString data = new FixedLengthString(7, ' ');
        final FixedLengthStringAttribute<Colour> attribute = new FixedLengthStringAttribute<>(Colour.class, 0, 7, Alignment.RIGHT);
        rightWriter.write(data, attribute, Colour.BLUE);
        assertEquals("   BLUE", data.getString());
    }
}

enum Colour {

    RED,
    GREEN,
    BLUE;
}