package com.github.tinosteinort.flda.interfaces.stringlist.reader;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LongAttributeReaderTest {

    private final LongAttributeReader reader = new LongAttributeReader();

    @Test public void readValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("999999999999999999");
        final StringListAttribute<Long> attribute = new StringListAttribute<>(Long.class, 0);

        assertEquals(999999999999999999L, (long) reader.read(record, attribute));
    }

    @Test public void readNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<Long> attribute = new StringListAttribute<>(Long.class, 0);

        assertNull(reader.read(record, attribute));
    }
}
