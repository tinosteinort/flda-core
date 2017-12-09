package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringFitterTest {

    @Test public void fitFillUpRight() {
        assertEquals("Abc  ", StringFitter.fit("Abc", Alignment.LEFT, 5, ' '));
    }

    @Test public void fitFillUpRightWithOtherFiller() {
        assertEquals("Abc__", StringFitter.fit("Abc", Alignment.LEFT, 5, '_'));
    }

    @Test public void fitCutRight() {
        assertEquals("AbcDe", StringFitter.fit("AbcDeF", Alignment.LEFT, 5, ' '));
    }

    @Test public void fitFillUpLeft() {
        assertEquals("  deF", StringFitter.fit("deF", Alignment.RIGHT, 5, ' '));
    }

    @Test public void fitFillUpLeftWithOtherFiller() {
        assertEquals("__deF", StringFitter.fit("deF", Alignment.RIGHT, 5, '_'));
    }

    @Test public void fitCutLeft() {
        assertEquals("2345", StringFitter.fit("12345", Alignment.RIGHT, 4, ' '));
    }

    @Test public void fitFillUpNull() {
        assertEquals("   ", StringFitter.fit(null, Alignment.LEFT, 3, ' '));
    }
}
