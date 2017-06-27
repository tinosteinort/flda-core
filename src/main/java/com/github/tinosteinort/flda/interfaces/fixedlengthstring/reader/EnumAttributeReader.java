package com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class EnumAttributeReader<T extends Enum<T>> implements AttributeReader<FixedLengthString, T, FixedLengthStringAttribute<T>> {

    private final StringReader reader = new StringReader();
    private final Class<T> enumClass;

    public EnumAttributeReader(final Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override public T read(final FixedLengthString data, final FixedLengthStringAttribute<T> attribute) {
        final String value = reader.read(data, attribute);
        if (value.equals("")) {
            return null;
        }
        return Enum.valueOf(enumClass, value);
    }
}
