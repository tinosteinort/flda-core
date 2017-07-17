package com.github.tinosteinort.flda.interfaces.stringlist.reader;


import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;

import java.util.List;

public class LongAttributeReader implements AttributeReader<List<String>, Long, StringListAttribute<Long>> {

    @Override public Long read(final List<String> record, final StringListAttribute<Long> attribute) {
        final String value = record.get(attribute.getIndex());
        if (value == null || "".equals(value.trim())) {
            return null;
        }
        return Long.valueOf(value);
    }
}
