package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class IntegerAttributeReader implements AttributeReader<FixedLengthString, Integer, FixedLengthStringAttribute<Integer>> {

    @Override public Integer read(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute) {
        final String value = data.getString().substring(attribute.getIndex(), attribute.getIndex() + attribute.getLength()).trim();
        if (value.equals("")) {
            return null;
        }
        return Integer.valueOf(value);
    }
}
