package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class StringAttributeReader implements AttributeReader<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public String read(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute) {
        return data.getString().substring(attribute.getIndex(), attribute.getIndex() + attribute.getLength()).trim();
    }
}
