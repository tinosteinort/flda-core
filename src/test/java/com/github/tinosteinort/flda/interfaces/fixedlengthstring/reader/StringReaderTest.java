package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringReaderTest {

    private final FixedLengthString data = new FixedLengthString("123   AbCd");
    private static final FixedLengthStringAttribute<String> STRING_ONE = new FixedLengthStringAttribute<>(String.class, 0, 3);
    private static final FixedLengthStringAttribute<String> STRING_TWO = new FixedLengthStringAttribute<>(String.class, 3, 3);
    private static final FixedLengthStringAttribute<String> STRING_THREE = new FixedLengthStringAttribute<>(String.class, 6, 4);

    private final StringReader reader = new StringReader();

    @Test public void readOne() {
        assertEquals("123", reader.read(data, STRING_ONE));
    }

    @Test public void readTwo() {
        assertEquals("", reader.read(data, STRING_TWO));
    }

    @Test public void readThree() {
        assertEquals("AbCd", reader.read(data, STRING_THREE));
    }
}
