package com.github.tinosteinort.flda.accessor.tupel;

import com.github.tinosteinort.flda.accessor.AttributeWriter;

public class IntegerTupelAttributeWriter implements AttributeWriter<Tupel, Integer, TupelAttribute<Integer>> {

    @Override public void write(final Tupel data, final TupelAttribute<Integer> attribute, final Integer value) {
        data.set(attribute.getIndex(), value);
    }
}
