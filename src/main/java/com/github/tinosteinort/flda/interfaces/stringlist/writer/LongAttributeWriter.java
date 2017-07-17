package com.github.tinosteinort.flda.interfaces.stringlist.writer;


import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;

import java.util.List;

public class LongAttributeWriter implements AttributeWriter<List<String>, Long, StringListAttribute<Long>> {

    @Override public void write(final List<String> record, final StringListAttribute<Long> attribute, final Long value) {
        final String stringValue = value == null ? null : String.valueOf(value);
        record.set(attribute.getIndex(), stringValue);
    }
}
