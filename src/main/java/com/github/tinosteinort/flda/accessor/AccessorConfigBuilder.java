package com.github.tinosteinort.flda.accessor;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the registry for all readers and writers for a specific record type.
 * @param <RECORD_TYPE> The type of a data row, which should be accessed.
 * @param <ATTRIBUTE_DESCRIPTION_TYPE> The type of the AttributeDescription.
 */
public class AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    protected final Map<Class<?>, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
    protected final Map<Class<?>, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
    protected final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
    protected final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();
    protected RecordFactory<RECORD_TYPE> recordFactory;
    protected RecordValidator<RECORD_TYPE> readValidator;
    protected RecordValidator<RECORD_TYPE> writeValidator;

    /**
     * Creates a empty {@code AccessorConfigBuilder} without any registerd Readers and Writers.
     */
    public AccessorConfigBuilder() {

    }

    /**
     * Creates a new Instance with the Readers and Writers of the given {@code baseConfig}.
     * @param baseConfig A base Configuration from which the Readers and Writers should be used.
     */
    public AccessorConfigBuilder(final AccessorConfig<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> baseConfig) {
        readersByType.putAll(baseConfig.readers());
        writersByType.putAll(baseConfig.writers());
        this.recordFactory = baseConfig.recordFactory();
        this.readValidator = baseConfig.readValidator();
        this.writeValidator = baseConfig.writeValidator();
    }

    /**
     * Registers a Reader for the given Type.
     * @param type The Type of the Attribute that should be read.
     * @param reader The Reader that should read Attributes of the given Type.
     * @param <T> The Type of the Attribute.
     * @return The Builder.
     */
    public <T> AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final Class<T> type,
            final AttributeReader<RECORD_TYPE, T, ? extends Attribute<T>> reader) {
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
    public <T> AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final Class<T> type,
           final AttributeWriter<RECORD_TYPE, T, ? extends Attribute<T>> writer) {
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
    public <T> AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(
            final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeReader<RECORD_TYPE, T, ? extends Attribute<T>> reader) {
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
    public <T> AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(
            final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeWriter<RECORD_TYPE, T, ? extends Attribute<T>> writer) {
        writersByAttribute.put(attribute, writer);
        return this;
    }

    /**
     * Sets a Factory which can create a new Instance of a Record.
     *
     * @param recordFactory the factory which can create records of the given type.
     * @return The Builder.
     */
    public AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> withRecordFactory(
            final RecordFactory<RECORD_TYPE> recordFactory) {
        this.recordFactory = recordFactory;
        return this;
    }

    /**
     * Sets a Validator for the {@link ReadAccessor}. The validator is executed
     *  whenever a new instance of a {@link ReadAccessor} is created.
     *
     * @param validator the validator which should validate a record.
     * @return The Builder.
     */
    public AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> withReadValidator(
            final RecordValidator<RECORD_TYPE> validator) {
        this.readValidator = validator;
        return this;
    }

    /**
     * Sets a Validator for the {@link WriteAccessor}. The validator is executed
     *  whenever a new instance of a {@link WriteAccessor} is created.
     *
     * @param validator the validator which should validate a record.
     * @return The Builder.
     */
    public AccessorConfigBuilder<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> withWriteValidator(
            final RecordValidator<RECORD_TYPE> validator) {
        this.writeValidator = validator;
        return this;
    }

    /**
     * Creates a new {@link AccessorConfig} with the registered Reader and Writer.
     * @return The new created {@link AccessorConfig}.
     */
    public AccessorConfig<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> build() {
        return new AccessorConfig<RECORD_TYPE, ATTRIBUTE_DESCRIPTION_TYPE>(readersByType, writersByType,
                readersByAttribute, writersByAttribute, recordFactory, readValidator, writeValidator) {
        };
    }
}
