package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntegerAttributeReaderTest {

    private final FixedLengthString data = new FixedLengthString("1234    -567");
    private static final FixedLengthStringAttribute<Integer> NUMBER_ONE = new FixedLengthStringAttribute<>(Integer.class, 0, 4);
    private static final FixedLengthStringAttribute<Integer> NUMBER_TWO = new FixedLengthStringAttribute<>(Integer.class, 4, 4);
    private static final FixedLengthStringAttribute<Integer> NUMBER_THREE = new FixedLengthStringAttribute<>(Integer.class, 8, 4);

    private final IntegerAttributeReader reader = new IntegerAttributeReader();

    @Test public void readOne() {
        assertEquals((Integer) 1234, reader.read(data, NUMBER_ONE));
    }

    @Test public void readTwo() {
        assertNull(reader.read(data, NUMBER_TWO));
    }

    @Test public void readThree() {
        assertEquals((Integer) (-567), reader.read(data, NUMBER_THREE));
    }
}
