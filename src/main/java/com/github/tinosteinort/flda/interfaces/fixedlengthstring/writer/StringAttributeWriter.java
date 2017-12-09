package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class StringAttributeWriter
        implements AttributeWriter<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute,
                                final String value) {

        final String newValue = StringFitter.fit(value, attribute.getAlignment(), attribute.getLength(),
                attribute.getFiller());

        data.update(attribute.getIndex(), newValue);
    }
}
