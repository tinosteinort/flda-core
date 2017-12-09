package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test public void fitFillUpRight() {
        assertEquals("Abc  ", StringUtils.fit("Abc", Alignment.LEFT, 5, ' '));
    }

    @Test public void fitFillUpRightWithOtherFiller() {
        assertEquals("Abc__", StringUtils.fit("Abc", Alignment.LEFT, 5, '_'));
    }

    @Test public void fitCutRight() {
        assertEquals("AbcDe", StringUtils.fit("AbcDeF", Alignment.LEFT, 5, ' '));
    }

    @Test public void fitFillUpLeft() {
        assertEquals("  deF", StringUtils.fit("deF", Alignment.RIGHT, 5, ' '));
    }

    @Test public void fitFillUpLeftWithOtherFiller() {
        assertEquals("__deF", StringUtils.fit("deF", Alignment.RIGHT, 5, '_'));
    }

    @Test public void fitCutLeft() {
        assertEquals("2345", StringUtils.fit("12345", Alignment.RIGHT, 4, ' '));
    }

    @Test public void fitFillUpNull() {
        assertEquals("   ", StringUtils.fit(null, Alignment.LEFT, 3, ' '));
    }
}
