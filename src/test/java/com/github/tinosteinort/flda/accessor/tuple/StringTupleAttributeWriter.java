package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.AttributeWriter;

public class StringTupleAttributeWriter implements AttributeWriter<Tuple, String, TupleAttribute<String>> {

    @Override public void write(final Tuple data, final TupleAttribute<String> attribute, final String value) {
        data.set(attribute.getIndex(), value);
    }
}
