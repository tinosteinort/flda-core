package com.github.tinosteinort.flda.interfaces.stringlist.reader;


import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;

import java.util.List;

public class EnumAttributeReader<T extends Enum<T>> implements AttributeReader<List<String>, T, StringListAttribute<T>> {

    private final Class<T> enumClass;

    public EnumAttributeReader(final Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override public T read(final List<String> record, final StringListAttribute<T> attribute) {
        final String value = record.get(attribute.getIndex());
        if (value == null || "".equals(value.trim())) {
            return null;
        }
        return Enum.valueOf(enumClass, value);
    }
}
