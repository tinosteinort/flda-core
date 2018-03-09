package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.AttributeReader;

public class StringTupleAttributeReader implements AttributeReader<Tuple, String, TupleAttribute<String>> {

    @Override public String read(final Tuple data, final TupleAttribute<String> attribute) {
        return (String) data.get(attribute.getIndex());
    }
}
