package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class RightAlignedIntegerAttributeWriter implements AttributeWriter<FixedLengthString, Integer, FixedLengthStringAttribute<Integer>> {

    private final StringFitter stringFitter = new StringFitter(StringFitter.Alignment.RIGHT, ' ');

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute, final Integer value) {

        final String before = data.getString().substring(0, attribute.getIndex());
        final String after = data.getString().substring(attribute.getIndex() + attribute.getLength());
        final String newValue = before + fillOrCut(value, attribute) + after;

        data.update(newValue);
    }

    private String fillOrCut(final Integer intValue, final FixedLengthStringAttribute<Integer> attribute) {
        return stringFitter.fit(String.valueOf(intValue), attribute.getLength());
    }
}
