package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class IntegerAttributeWriter implements AttributeWriter<FixedLengthString, Integer, FixedLengthStringAttribute<Integer>> {

    private final StringFitter stringFitter;

    public IntegerAttributeWriter() {
        this.stringFitter = new StringFitter(StringFitter.Alignment.LEFT, ' ');
    }

    public IntegerAttributeWriter(final StringFitter.Alignment alignment, final char filler) {
        this.stringFitter = new StringFitter(alignment, filler);
    }

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute, final Integer value) {

        data.update(attribute.getIndex(), stringFitter.fit(String.valueOf(value), attribute.getLength()));
    }
}
