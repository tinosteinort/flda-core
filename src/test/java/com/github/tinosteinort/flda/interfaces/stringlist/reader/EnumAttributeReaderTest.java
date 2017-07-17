package com.github.tinosteinort.flda.interfaces.stringlist.reader;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumAttributeReaderTest {

    private final EnumAttributeReader<FirstNumber> reader = new EnumAttributeReader<>(FirstNumber.class);

    @Test public void validValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("TWO");
        final StringListAttribute<FirstNumber> attribute = new StringListAttribute<>(FirstNumber.class, 0);

        assertEquals(FirstNumber.TWO, reader.read(record, attribute));
    }

    @Test public void readNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<FirstNumber> attribute = new StringListAttribute<>(FirstNumber.class, 0);

        assertNull(reader.read(record, attribute));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("TEN");
        final StringListAttribute<FirstNumber> attribute = new StringListAttribute<>(FirstNumber.class, 0);

        reader.read(record, attribute);
    }
}

enum FirstNumber {

    ONE,
    TWO,
    THREE;
}