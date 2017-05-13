package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import org.junit.Assert;
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
        assertEquals("AbcDe", rightAlignedFitter.fit("AbcDeF", 5));
    }

    @Test public void fitFillUpNull() {
        assertEquals("   ", leftAlignedFitter.fit(null, 3));
    }
}
