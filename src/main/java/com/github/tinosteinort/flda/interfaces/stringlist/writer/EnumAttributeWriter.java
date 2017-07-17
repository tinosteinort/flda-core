package com.github.tinosteinort.flda.interfaces.stringlist.writer;

import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;

import java.util.List;

public class EnumAttributeWriter<T extends Enum<T>> implements AttributeWriter<List<String>, T, StringListAttribute<T>> {

    @Override public void write(final List<String> record, final StringListAttribute<T> attribute, final T value) {
        final String enumValue = value == null ? null : value.name();
        record.set(attribute.getIndex(), enumValue);
    }
}
