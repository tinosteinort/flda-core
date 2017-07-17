package com.github.tinosteinort.flda.interfaces.stringlist.writer;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumAttributeWriterTest {

    private final EnumAttributeWriter<FirstNumber> writer = new EnumAttributeWriter<>();

    @Test public void writeValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<FirstNumber> attribute = new StringListAttribute<>(FirstNumber.class, 0);

        writer.write(record, attribute, FirstNumber.ONE);
        assertEquals("ONE", record.get(0));
    }

    @Test public void writeNull() {
        final List<String> record = new ArrayList<>(1);
        record.add("SomeValue");
        final StringListAttribute<FirstNumber> attribute = new StringListAttribute<>(FirstNumber.class, 0);

        writer.write(record, attribute, null);
        assertNull(record.get(0));
    }
}

enum FirstNumber {

    ONE,
    TWO,
    THREE;
}