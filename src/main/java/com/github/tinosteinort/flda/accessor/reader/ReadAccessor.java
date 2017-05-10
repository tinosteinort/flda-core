package com.github.tinosteinort.flda.accessor.reader;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.Attribute;

import java.util.Optional;

public class ReadAccessor<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config;
    private final TUPEL_TYPE data;

    public ReadAccessor(final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config, final TUPEL_TYPE data) {
        this.config = config;
        this.data = data;
    }

    public <T, ATTR extends Attribute<T>> T read(final ATTR attribute) {
        return (T) reader((ATTR_TYPE) attribute).read(data, (ATTR_TYPE) attribute);
    }

    private <T> AttributeReader<TUPEL_TYPE, T, ATTR_TYPE> reader(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeReader<TUPEL_TYPE, T, ATTR_TYPE>) config.readerFor(attribute))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute));
    }
}
