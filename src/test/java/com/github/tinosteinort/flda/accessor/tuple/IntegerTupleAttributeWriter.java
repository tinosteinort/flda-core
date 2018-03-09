package com.github.tinosteinort.flda.accessor.tuple;

import com.github.tinosteinort.flda.accessor.AttributeWriter;

public class IntegerTupleAttributeWriter implements AttributeWriter<Tuple, Integer, TupleAttribute<Integer>> {

    @Override public void write(final Tuple data, final TupleAttribute<Integer> attribute, final Integer value) {
        data.set(attribute.getIndex(), value);
    }
}
