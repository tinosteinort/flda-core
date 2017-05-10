package com.github.tinosteinort.flda.accessor.writer;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.Attribute;

import java.util.Optional;

public class WriteAccessor<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config;
    private final TUPEL_TYPE data;

    public WriteAccessor(final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config, final TUPEL_TYPE data) {
        this.config = config;
        this.data = data;
    }

    public <T, ATTR extends Attribute<T>> void write(final ATTR attribute, final T value) {
        writer((ATTR_TYPE) attribute).write(data, (ATTR_TYPE) attribute, value);
    }

    private <T> AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE> writer(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE>) config.writerFor(attribute))
                .orElseThrow(() -> new RuntimeException("No Writer available for Type " + attribute));
    }
}
