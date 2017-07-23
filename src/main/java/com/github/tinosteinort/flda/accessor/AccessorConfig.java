package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Configuration of the Writer and Reader of an Interface.
 *
 * @param <TUPEL_TYPE> The Type of Data that should be accessed.
 * @param <ATTR_DESC_TYPE> The Type of the Description of an Attribute.
 */
public interface AccessorConfig<TUPEL_TYPE, ATTR_DESC_TYPE extends Attribute<?>> {

    /**
     * Determines the Reader for the given Attribute. If a Reader is registered for a Type and a specific
     *  Attribute, the Reader for the specific Attribute is returned.
     * @param attribute The Attribute for which the Reader is needed.
     * @param <ATTR_TYPE> The Type of the Attribute.
     * @return The Reader for the given Attribute.
     */
    <ATTR_TYPE> AttributeReader<TUPEL_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> readerFor(ATTR_DESC_TYPE attribute);

    /**
     * Determines the Writer for the given Attribute. If a Writer is registered for a Type and a specific
     *  Attribute, the Writer for the specific Attribute is returned.
     * @param attribute The Attribute for which the Writer is needed.
     * @param <ATTR_TYPE> The Type of the Attribute.
     * @return The Writer for the given Attribute.
     */
    <ATTR_TYPE> AttributeWriter<TUPEL_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> writerFor(ATTR_DESC_TYPE attribute);

    /**
     * Creates a new Instance of a Record, which is usable by the
     *  {@link com.github.tinosteinort.flda.accessor.reader.ReadAccessor}
     *  and {@link com.github.tinosteinort.flda.accessor.writer.WriteAccessor}. To use this Method it is required
     *  to register a Record Factory with {@link AccessorConfigBuilder#withRecordFactory(Supplier)}.
     *
     * @throws RuntimeException If no Record Factory is set
     * @return A new Record Instance
     */
    TUPEL_TYPE createNewRecord();

    /**
     * @return All registered Readers.
     */
    Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readers();

    /**
     * @return All registered Writers.
     */
    Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writers();
}
