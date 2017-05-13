package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RightAlignedIntegerAttributeWriterTest {

    private static final FixedLengthStringAttribute<Integer> INTEGER_ONE = new FixedLengthStringAttribute<>(Integer.class, 0, 3);

    private final RightAlignedIntegerAttributeWriter writer = new RightAlignedIntegerAttributeWriter();

    @Test public void writeRightAlign() {
        final FixedLengthString data = new FixedLengthString("   ");
        writer.write(data, INTEGER_ONE, 23);
        assertEquals(" 23", data.getString());
    }

    @Test public void writeRightAlignWithCut() {
        final FixedLengthString data = new FixedLengthString("   ");
        writer.write(data, INTEGER_ONE, 2345);
        assertEquals("234", data.getString());
    }
}
