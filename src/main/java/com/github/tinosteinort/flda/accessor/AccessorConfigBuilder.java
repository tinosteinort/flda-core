package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * This is the Registry for all Readers and Writers for a specific Tupel Type.
 * @param <TUPEL_TYPE> The Type of a Data Row, which should be accessed.
 * @param <ATTRIBUTE_DESCRIPTION_TYPE> The Type of the AttributeDescription.
 */
public class AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    private final Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
    private final Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
    private final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
    private final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();
    private Supplier<TUPEL_TYPE> recordFactory;

    /**
     * Creates a empty {@code AccessorConfigBuilder} without any registerd Readers and Writers.
     */
    public AccessorConfigBuilder() {

    }

    /**
     * Creates a new Instance with the Readers and Writers of the given {@code baseConfig}.
     * @param baseConfig A base Configuration from which the Readers and Writers should be used.
     */
    public AccessorConfigBuilder(final AccessorConfig<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> baseConfig) {
        readersByType.putAll(baseConfig.readers());
        writersByType.putAll(baseConfig.writers());
    }

    /**
     * Registers a Reader for the given Type.
     * @param type The Type of the Attribute that should be read.
     * @param reader The Reader that should read Attributes of the given Type.
     * @param <T> The Type of the Attribute.
     * @return The Builder.
     */
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final Class<T> type,
            final AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> reader) {
        readersByType.put(type, reader);
        return this;
    }

    /**
     * Registers a Writer for the given Type.
     * @param type The Type of the Attribute that should be written.
     * @param writer The Writer that should write the Attribute.
     * @param <T> The Type of the Attribute.
     * @return The Builder.
     */
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final Class<T> type,
            final AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writer) {
        writersByType.put(type, writer);
        return this;
    }

    /**
     * Registers a Reader for the given Attribute.
     * @param attribute The Type of the Attribute that should be read.
     * @param reader The Reader that should read Attributes of the given Type.
     * @param <T> The Type of the Attribute.
     * @return The Builder.
     */
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> reader) {
        readersByAttribute.put(attribute, reader);
        return this;
    }

    /**
     * Registers a Writer for the given Attribute.
     * @param attribute The Type of the Attribute that should be written.
     * @param writer The Writer that should write the Attribute.
     * @param <T> The Type of the Attribute.
     * @return The Builder.
     */
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writer) {
        writersByAttribute.put(attribute, writer);
        return this;
    }

    /**
     * Sets a Factory which can create a new Instance of a Record.
     *
     * @param recordFactory
     * @return
     */
    public AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> withRecordFactory(final Supplier<TUPEL_TYPE> recordFactory) {
        this.recordFactory = recordFactory;
        return this;
    }

    /**
     * Creates a new {@link AccessorConfig} with the registered Reader and Writer.
     * @return The new created {@link AccessorConfig}.
     */
    public AccessorConfig<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> build() {
        return new AccessorConfigImpl<>(readersByType, writersByType, readersByAttribute, writersByAttribute, recordFactory);
    }

    private class AccessorConfigImpl<TYPE, ATTR_DESC_TYPE extends Attribute<?>> implements AccessorConfig<TYPE, ATTR_DESC_TYPE> {

        private final Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
        private final Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
        private final Map<ATTR_DESC_TYPE, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
        private final Map<ATTR_DESC_TYPE, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();
        private final Supplier<TYPE> recordFactory;

        private AccessorConfigImpl(
                final Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByType,
                final Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByType,
                final Map<ATTR_DESC_TYPE, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByAttribute,
                final Map<ATTR_DESC_TYPE, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByAttribute,
                final Supplier<TYPE> recordFactory) {
            this.readersByType.putAll(readersByType);
            this.writersByType.putAll(writersByType);
            this.readersByAttribute.putAll(readersByAttribute);
            this.writersByAttribute.putAll(writersByAttribute);
            this.recordFactory = recordFactory;
        }

        @Override public <ATTR_TYPE> AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE> readerFor(final ATTR_DESC_TYPE attribute) {
            final AttributeReader<TYPE, ?, ? extends Attribute<?>> readerByAttribute = readersByAttribute.get(attribute);
            if (readerByAttribute == null) {
                return (AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readersByType.get(attribute.getType());
            }
            return (AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readerByAttribute;
        }

        @Override public <ATTR_TYPE> AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE> writerFor(final ATTR_DESC_TYPE attribute) {
            final AttributeWriter<TYPE, ?, ? extends Attribute<?>> writerByAttribute = writersByAttribute.get(attribute);
            if (writerByAttribute == null) {
                return (AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writersByType.get(attribute.getType());
            }
            return (AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writerByAttribute;
        }

        @Override public TYPE createNewRecord() {
            if (recordFactory == null) {
                throw new RuntimeException("Could not create record instance without RecordFactory");
            }
            return recordFactory.get();
        }

        public Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readers() {
            return Collections.unmodifiableMap(readersByType);
        }

        public Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writers() {
            return Collections.unmodifiableMap(writersByType);
        }
    }
}
