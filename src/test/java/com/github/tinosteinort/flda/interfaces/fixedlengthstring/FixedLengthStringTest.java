package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedLengthStringTest {

    @Test public void testEquals() {
        assertEquals(new FixedLengthString("   "), new FixedLengthString("   "));
    }

    @Test public void testEqualsDifferentInitialised() {
        assertEquals(new FixedLengthString("###"), new FixedLengthString(3, '#'));
    }

    @Test public void update() {
        final FixedLengthString data = new FixedLengthString(5, ' ');
        data.update(0, "-----");
        assertEquals("-----", data.getString());
    }

    @Test public void updateMiddle() {
        final FixedLengthString data = new FixedLengthString(5, ' ');
        data.update(1, "--");
        assertEquals(" --  ", data.getString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTooLong() {
        final FixedLengthString data = new FixedLengthString(5, ' ');
        data.update(0, "123456");
    }
}
