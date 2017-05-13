package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerAttributeWriterTest {

    private static final FixedLengthStringAttribute<Integer> INTEGER_ONE = new FixedLengthStringAttribute<>(Integer.class, 0, 3);
    private static final FixedLengthStringAttribute<Integer> INTEGER_TWO = new FixedLengthStringAttribute<>(Integer.class, 3, 3);
    private static final FixedLengthStringAttribute<Integer> INTEGER_THREE = new FixedLengthStringAttribute<>(Integer.class, 6, 4);

    private final IntegerAttributeWriter writer = new IntegerAttributeWriter();

    @Test public void writeOne() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, INTEGER_ONE, 123);
        assertEquals("123       ", data.getString());
    }

    @Test public void writeTwo() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, INTEGER_TWO, 45);
        assertEquals("   45     ", data.getString());
    }

    @Test public void writeTwoWithCut() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, INTEGER_TWO, 456123);
        assertEquals("   456    ", data.getString());
    }

    @Test public void writeThree() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, INTEGER_THREE, 7890);
        assertEquals("      7890", data.getString());
    }

    @Test public void writeThreeWithCut() {
        final FixedLengthString data = new FixedLengthString("          ");
        writer.write(data, INTEGER_THREE, 7890123);
        assertEquals("      7890", data.getString());
    }
}
