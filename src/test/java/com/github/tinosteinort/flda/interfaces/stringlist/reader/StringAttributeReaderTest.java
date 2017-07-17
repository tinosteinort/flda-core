package com.github.tinosteinort.flda.interfaces.stringlist.reader;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringAttributeReaderTest {

    private final StringAttributeReader reader = new StringAttributeReader();

    @Test public void readValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("Abc");
        final StringListAttribute<String> attribute = new StringListAttribute<>(String.class, 0);

        assertEquals("Abc", reader.read(record, attribute));
    }

    @Test public void readNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<String> attribute = new StringListAttribute<>(String.class, 0);

        assertNull(reader.read(record, attribute));
    }
}
