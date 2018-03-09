package com.github.tinosteinort.flda.accessor.tupel;

import com.github.tinosteinort.flda.accessor.AttributeReader;

public class IntegerTupelAttributeReader implements AttributeReader<Tupel, Integer, TupelAttribute<Integer>> {

    @Override public Integer read(final Tupel data, final TupelAttribute<Integer> attribute) {
        return Integer.valueOf((String) data.get(attribute.getIndex()));
    }
}
