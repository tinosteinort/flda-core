package com.github.tinosteinort.flda.accessor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Configuration of the Writer and Reader of an Interface.
 *
 * @param <RECORD_TYPE> The Type of Data that should be accessed.
 * @param <ATTR_DESC_TYPE> The Type of the Description of an Attribute.
 */
public abstract class AccessorConfig<RECORD_TYPE, ATTR_DESC_TYPE extends Attribute<?>> {

    private final Map<Class<?>, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
    private final Map<Class<?>, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
    private final Map<ATTR_DESC_TYPE, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
    private final Map<ATTR_DESC_TYPE, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();
    private final RecordFactory<RECORD_TYPE> recordFactory;
    private RecordValidator<RECORD_TYPE> readValidator;
    private RecordValidator<RECORD_TYPE> writeValidator;

    protected AccessorConfig(
            final Map<Class<?>, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByType,
            final Map<Class<?>, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByType,
            final Map<ATTR_DESC_TYPE, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readersByAttribute,
            final Map<ATTR_DESC_TYPE, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writersByAttribute,
            final RecordFactory<RECORD_TYPE> recordFactory,
            final RecordValidator<RECORD_TYPE> readValidator,
            final RecordValidator<RECORD_TYPE> writeValidator) {

        this.readersByType.putAll(readersByType);
        this.writersByType.putAll(writersByType);
        this.readersByAttribute.putAll(readersByAttribute);
        this.writersByAttribute.putAll(writersByAttribute);
        this.recordFactory = recordFactory;
        this.readValidator = readValidator;
        this.writeValidator = writeValidator;
    }

    /**
     * Determines the Reader for the given Attribute. If a Reader is registered for a Type and a specific
     *  Attribute, the Reader for the specific Attribute is returned.
     * @param attribute The Attribute for which the Reader is needed.
     * @param <ATTR_TYPE> The Type of the Attribute.
     * @return The Reader for the given Attribute.
     */
    protected <ATTR_TYPE> AttributeReader<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> readerFor(
            final ATTR_DESC_TYPE attribute) {
        final AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>> readerByAttribute = readersByAttribute
                .get(attribute);
        if (readerByAttribute == null) {
            return (AttributeReader<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readersByType.get(attribute.getType());
        }
        return (AttributeReader<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readerByAttribute;
    }

    /**
     * Determines the Writer for the given Attribute. If a Writer is registered for a Type and a specific
     *  Attribute, the Writer for the specific Attribute is returned.
     * @param attribute The Attribute for which the Writer is needed.
     * @param <ATTR_TYPE> The Type of the Attribute.
     * @return The Writer for the given Attribute.
     */
    protected <ATTR_TYPE> AttributeWriter<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> writerFor(
            final ATTR_DESC_TYPE attribute) {
        final AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>> writerByAttribute =
                writersByAttribute.get(attribute);
        if (writerByAttribute == null) {
            return (AttributeWriter<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writersByType.get(attribute.getType());
        }
        return (AttributeWriter<RECORD_TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writerByAttribute;
    }

    /**
     * Creates a new Instance of a Record, which is usable by the
     *  {@link ReadAccessor}
     *  and {@link WriteAccessor}. To use this Method it is required
     *  to register a Record Factory with {@link AccessorConfigBuilder#withRecordFactory(RecordFactory)}.
     *
     * @throws RuntimeException If no Record Factory is set
     * @return A new Record Instance
     */
    public RECORD_TYPE createNewRecord() {
        if (recordFactory == null) {
            throw new RuntimeException("Could not create record instance without RecordFactory");
        }
        return recordFactory.createNewRecord();
    }

    /**
     * Execute the validation for a record when a {@link ReadAccessor}
     *  is created.
     * @param record the record for which the validation should be executed.
     */
    protected void validateForRead(final RECORD_TYPE record) {
        if (readValidator != null) {
            readValidator.validate(record);
        }
    }

    /**
     * Execute the validation for a record when a {@link WriteAccessor}
     *  is created.
     * @param record the record for which the validation should be executed.
     */
    protected void validateForWrite(final RECORD_TYPE record) {
        if (writeValidator != null) {
            writeValidator.validate(record);
        }
    }

    /**
     * @return All registered Readers.
     */
    protected Map<Class<?>, AttributeReader<RECORD_TYPE, ?, ? extends Attribute<?>>> readers() {
        return Collections.unmodifiableMap(readersByType);
    }

    /**
     * @return All registered Writers.
     */
    protected Map<Class<?>, AttributeWriter<RECORD_TYPE, ?, ? extends Attribute<?>>> writers() {
        return Collections.unmodifiableMap(writersByType);
    }

    protected RecordFactory<RECORD_TYPE> recordFactory() {
        return recordFactory;
    }

    protected RecordValidator<RECORD_TYPE> readValidator() {
        return readValidator;
    }

    protected RecordValidator<RECORD_TYPE> writeValidator() {
        return writeValidator;
    }

    /**
     * Creates a new {@link ReadAccessor} for the given record.
     *
     * @param record to read from
     * @return a new {@code ReadAccessor} for the record.
     */
    public ReadAccessor<RECORD_TYPE, ATTR_DESC_TYPE> newReadAccessor(final RECORD_TYPE record) {
        return new ReadAccessor<>(this, record);
    }

    /**
     * Creates a new {@link WriteAccessor} for the given record.
     *
     * @param record to write to
     * @return a new {@code WriteAccessor} for the record.
     */
    public WriteAccessor<RECORD_TYPE, ATTR_DESC_TYPE> newWriteAccessor(final RECORD_TYPE record) {
        return new WriteAccessor<>(this, record);
    }
}
