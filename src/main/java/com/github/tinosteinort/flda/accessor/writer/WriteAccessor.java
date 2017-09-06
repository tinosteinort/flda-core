package com.github.tinosteinort.flda.accessor.writer;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.Attribute;

import java.util.Optional;

/**
 * A {@code WriteAccessor} only writes Data into a Data Record. A new Instance of {@code WriteAccessor} is
 *  needed for every Record. The {@code WriteAccessor} allows to access the Data by the Use of Attributes
 *  which describes the Data Record. The {@link AccessorConfig} is needed to get the Writer for the Attributes.
 * @param <TUPEL_TYPE> The Type of the Data Record.
 * @param <ATTR_TYPE> The Description Type of an Attribute.
 */
public class WriteAccessor<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config;
    private final TUPEL_TYPE data;

    /**
     * Creates a new {@link WriteAccessor} with the given Configuration for the given DataRow.
     * @param config The {@link AccessorConfig} with the registered Writers.
     * @param data The DataRow into which should be written.
     */
    public WriteAccessor(final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config, final TUPEL_TYPE data) {
        this.config = config;
        this.data = data;
        this.config.validateForWrite(data);
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
