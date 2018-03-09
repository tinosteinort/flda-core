package com.github.tinosteinort.flda.accessor.tupel;

import com.github.tinosteinort.flda.accessor.AttributeWriter;

public class StringTupelAttributeWriter implements AttributeWriter<Tupel, String, TupelAttribute<String>> {

    @Override public void write(final Tupel data, final TupelAttribute<String> attribute, final String value) {
        data.set(attribute.getIndex(), value);
    }
}
