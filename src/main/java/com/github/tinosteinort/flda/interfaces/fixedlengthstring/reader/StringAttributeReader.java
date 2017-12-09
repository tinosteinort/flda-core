package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringUtils;

public class StringAttributeReader
        implements AttributeReader<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public String read(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute) {
        final String value = StringUtils.readAndTrim(data, attribute.getIndex(), attribute.getLength(),
                attribute.getFiller());
        if (value.isEmpty()) {
            return null;
        }
        return value;
    }
}
