package com.github.tinosteinort.flda.interfaces.stringlist.writer;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LongAttributeWriterTest {

    private final LongAttributeWriter writer = new LongAttributeWriter();

    @Test public void writeValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<Long> attribute = new StringListAttribute<>(Long.class, 0);

        writer.write(record, attribute, 999999999999999999L);

        assertEquals("999999999999999999", record.get(0));
    }

    @Test public void writeNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("SomeValue");
        final StringListAttribute<Long> attribute = new StringListAttribute<>(Long.class, 0);

        writer.write(record, attribute, null);

        assertNull(record.get(0));
    }
}
