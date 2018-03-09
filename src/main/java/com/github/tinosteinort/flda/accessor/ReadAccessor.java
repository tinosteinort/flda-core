package com.github.tinosteinort.flda.accessor;

import java.util.Optional;

/**
 * A {@code ReadAccessor} only read Data from a Data Record. A new Instance of {@code ReadAccessor} is
 *  needed for every Record. The {@code ReadAccessor} allows to access the Data by the Use of Attributes
 *  which describes the Data Record. The {@link AccessorConfig} is needed to get the Reader for the Attributes.
 * @param <RECORD_TYPE> The Type of the Data Record.
 * @param <ATTR_TYPE> The Description Type of an Attribute.
 */
public class ReadAccessor<RECORD_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<RECORD_TYPE, ATTR_TYPE> config;
    private final RECORD_TYPE data;

    /**
     * Creates a new {@link ReadAccessor} with the given Configuration for the given DataRow.
     * @param config The {@link AccessorConfig} with the registered Readers.
     * @param data The DataRow from which should be read.
     */
    public ReadAccessor(final AccessorConfig<RECORD_TYPE, ATTR_TYPE> config, final RECORD_TYPE data) {
        this.config = config;
        this.data = data;
        this.config.validateForRead(data);
    }

    public <T, ATTR extends Attribute<T>> T read(final ATTR attribute) {
        return (T) reader((ATTR_TYPE) attribute).read(data, (ATTR_TYPE) attribute);
    }

    private <T> AttributeReader<RECORD_TYPE, T, ATTR_TYPE> reader(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeReader<RECORD_TYPE, T, ATTR_TYPE>) config.readerFor(attribute))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute));
    }
}
