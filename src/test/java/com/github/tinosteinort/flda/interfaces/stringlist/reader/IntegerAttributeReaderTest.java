package com.github.tinosteinort.flda.interfaces.stringlist.reader;

import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntegerAttributeReaderTest {

    private final IntegerAttributeReader reader = new IntegerAttributeReader();

    @Test public void readValue() {
        final List<String> record = new ArrayList<>(1);
        record.add("468");
        final StringListAttribute<Integer> attribute = new StringListAttribute<>(Integer.class, 0);

        assertEquals(468, (int) reader.read(record, attribute));
    }

    @Test public void readNullValue() {
        final List<String> record = new ArrayList<>(1);
        record.add(null);
        final StringListAttribute<Integer> attribute = new StringListAttribute<>(Integer.class, 0);

        assertNull(reader.read(record, attribute));
    }
}
