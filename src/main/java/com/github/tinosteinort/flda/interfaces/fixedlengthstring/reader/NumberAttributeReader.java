package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public abstract class NumberAttributeReader<T extends Number>
        implements AttributeReader<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    private final StringReader reader = new StringReader();

    @Override public T read(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute) {
        final String value = reader.read(data, attribute);
        return convertToNumber(value);
    }

    protected T convertToNumber(final String value) {
        if (value.equals("")) {
            return null;
        }
        return nullSafeConvertToNumber(value);
    }

    protected abstract T nullSafeConvertToNumber(final String value);
}
