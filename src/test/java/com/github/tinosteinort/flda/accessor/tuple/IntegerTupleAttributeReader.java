package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.AttributeReader;

public class IntegerTupleAttributeReader implements AttributeReader<Tuple, Integer, TupleAttribute<Integer>> {

    @Override public Integer read(final Tuple data, final TupleAttribute<Integer> attribute) {
        return (Integer) data.get(attribute.getIndex());
    }
}
