package com.github.tinosteinort.flda.interfaces.stringlist.writer;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringAttributeWriterTest {

    private final StringAttributeWriter writer = new StringAttributeWriter();

    @Test public void writeValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<String> attribute = new StringListAttribute<>(String.class, 0);

        writer.write(record, attribute, "XYz");

        assertEquals("XYz", record.get(0));
    }

    @Test public void writeNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("SomeValue");
        final StringListAttribute<String> attribute = new StringListAttribute<>(String.class, 0);

        writer.write(record, attribute, null);

        assertNull(record.get(0));
    }
}
