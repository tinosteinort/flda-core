package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
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

    @Test public void readStart() {
        final FixedLengthString data = new FixedLengthString("123   AbCd");
        assertEquals("123", StringUtils.readAndTrim(data, 0, 3, ' '));
    }

    @Test public void readMiddle() {
        final FixedLengthString data = new FixedLengthString("123   AbCd");
        assertEquals("", StringUtils.readAndTrim(data, 3, 3, ' '));
    }

    @Test public void readEnd() {
        final FixedLengthString data = new FixedLengthString("123   AbCd");
        assertEquals("AbCd", StringUtils.readAndTrim(data, 6, 4, ' '));
    }

    @Test public void readAndTrimSpaceRight() {
        final FixedLengthString data = new FixedLengthString("12345  ");
        assertEquals("12345", StringUtils.readAndTrim(data, 0, 7, ' '));
    }

    @Test public void readAndTrimSpaceLeft() {
        final FixedLengthString data = new FixedLengthString("  12345");
        assertEquals("12345", StringUtils.readAndTrim(data, 0, 7, ' '));
    }

    @Test public void readAndTrimHashRight() {
        final FixedLengthString data = new FixedLengthString("12345##");
        assertEquals("12345", StringUtils.readAndTrim(data, 0, 7, '#'));
    }

    @Test public void readAndTrimHashLeft() {
        final FixedLengthString data = new FixedLengthString("##12345");
        assertEquals("12345", StringUtils.readAndTrim(data, 0, 7, '#'));
    }

    @Test public void findFirstNonFillerFirstChar() {
        assertEquals(0, StringUtils.findFirstNonFiller("1", '#'));
    }

    @Test public void findFirstNonFillerMiddleChar() {
        assertEquals(1, StringUtils.findFirstNonFiller("*1*", '*'));
    }

    @Test public void findFirstNonFillerLastChar() {
        assertEquals(4, StringUtils.findFirstNonFiller("____5", '_'));
    }

    @Test public void findFirstNonFillerNoCharFound() {
        assertEquals(-1, StringUtils.findFirstNonFiller("     ", ' '));
    }

    @Test public void findLastNonFillerFirstChar() {
        assertEquals(0, StringUtils.findLastNonFiller("1", '#'));
    }

    @Test public void findLastNonFillerMiddleChar() {
        assertEquals(1, StringUtils.findLastNonFiller("*1*", '*'));
    }

    @Test public void findLastNonFillerLastChar() {
        assertEquals(4, StringUtils.findLastNonFiller("____5", '_'));
    }

    @Test public void findLastNonFillerNoCharFound() {
        assertEquals(-1, StringUtils.findLastNonFiller("     ", ' '));
    }
}
