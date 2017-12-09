package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringFitterTest {

    private final StringFitter fitter = new StringFitter(' ');

    @Test public void fitLeftFillUp() {
        assertEquals("Abc  ", fitter.fit("Abc", Alignment.LEFT, 5));
    }
    @Test public void fitLeftCut() {
        assertEquals("AbcDe", fitter.fit("AbcDeF", Alignment.LEFT, 5));
    }

    @Test public void fitRightFillUp() {
        assertEquals("  deF", fitter.fit("deF", Alignment.RIGHT, 5));
    }
    @Test public void fitRightCut() {
        assertEquals("2345", fitter.fit("12345", Alignment.RIGHT, 4));
    }

    @Test public void fitFillUpNull() {
        assertEquals("   ", fitter.fit(null, Alignment.LEFT, 3));
    }
}
