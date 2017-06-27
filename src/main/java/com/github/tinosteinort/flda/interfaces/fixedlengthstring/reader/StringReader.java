package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class StringReader {

    public String read(final FixedLengthString data, final FixedLengthStringAttribute<?> attribute) {
        return data.getString().substring(
                    attribute.getIndex(),
                    attribute.getIndex() + attribute.getLength())
                .trim();
    }
}
