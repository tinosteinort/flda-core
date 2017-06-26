package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer.StringFitter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringFitterTest {

    private final StringFitter leftAlignedFitter = new StringFitter(StringFitter.Alignment.LEFT, ' ');
    private final StringFitter rightAlignedFitter = new StringFitter(StringFitter.Alignment.RIGHT, ' ');

    @Test public void fitLeftFillUp() {
        assertEquals("Abc  ", leftAlignedFitter.fit("Abc", 5));
    }
    @Test public void fitLeftCut() {
        assertEquals("AbcDe", leftAlignedFitter.fit("AbcDeF", 5));
    }

    @Test public void fitRightFillUp() {
        assertEquals("  deF", rightAlignedFitter.fit("deF", 5));
    }
    @Test public void fitRightCut() {
        assertEquals("2345", rightAlignedFitter.fit("12345", 4));
    }

    @Test public void fitFillUpNull() {
        assertEquals("   ", leftAlignedFitter.fit(null, 3));
    }
}
