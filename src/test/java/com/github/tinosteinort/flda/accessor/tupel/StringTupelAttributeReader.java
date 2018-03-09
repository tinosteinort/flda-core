package com.github.tinosteinort.flda.accessor.tupel;

import com.github.tinosteinort.flda.accessor.AttributeReader;

public class StringTupelAttributeReader implements AttributeReader<Tupel, String, TupelAttribute<String>> {

    @Override public String read(final Tupel data, final TupelAttribute<String> attribute) {
        return (String) data.get(attribute.getIndex());
    }
}
