package com.github.tinosteinort.flda.accessor;

/**
 * Interface for a Reader. Implementations defines how a specific Attribute should be read.
 * @param <RECORD_TYPE> The Type of the Data.
 * @param <ATTRIBUTE_TYPE> The Datatype of the Attribute.
 * @param <ATTRIBUTE_DESCRIPTION_TYPE> The Description of an Attribute.
 */
public interface AttributeReader<RECORD_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    /**
     * Reads a piece of Information out of {@code data}.
     * @param data The full Data'Row'.
     * @param attribute The Description of an Attribute. Defines how the Attribute should be read.
     * @return the determined piece of Data.
     */
    ATTRIBUTE_TYPE read(RECORD_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute);
}
