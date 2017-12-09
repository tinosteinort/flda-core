package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.StringUtils;

public class EnumAttributeReader<T extends Enum<T>> implements AttributeReader<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    private final Class<T> enumClass;

    public EnumAttributeReader(final Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override public T read(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute) {
        final String value = StringUtils.readAndTrim(data, attribute.getIndex(), attribute.getLength(),
                attribute.getFiller());
        if (value.isEmpty()) {
            return null;
        }
        return Enum.valueOf(enumClass, value);
    }
}
