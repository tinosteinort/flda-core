package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class StringAttributeWriter implements AttributeWriter<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    private final StringFitter stringFitter = new StringFitter(StringFitter.Alignment.LEFT, ' ');

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute, final String value) {

        data.update(attribute.getIndex(), stringFitter.fit(value, attribute.getLength()));
    }
}
