package com.github.tinosteinort.flda.accessor.reader;

import com.github.tinosteinort.flda.accessor.Attribute;

/**
 * Interface for a Reader. Implementations defines how a specific Attribute should be read.
 * @param <TUPEL_TYPE> The Type of the Data.
 * @param <ATTRIBUTE_TYPE> The Datatype of the Attribute.
 * @param <ATTRIBUTE_DESCRIPTION_TYPE> The Description of an Attribute.
 */
public interface AttributeReader<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    /**
     * Reads a piece of Information out of {@code data}.
     * @param data The full Data'Row'.
     * @param attribute The Description of an Attribute. Defines how the Attribute should be read.
     * @return the determined piece of Data.
     */
    ATTRIBUTE_TYPE read(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute);
}
