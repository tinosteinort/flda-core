package com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringFitter;

public class EnumAttributeWriter<T extends Enum<T>>
        implements AttributeWriter<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute,
                                final T value) {

        final String name = (value == null ? null : value.name());
        final String newValue = StringFitter.fit(name, attribute.getAlignment(), attribute.getLength(),
                attribute.getFiller());

        data.update(attribute.getIndex(), newValue);
    }
}
