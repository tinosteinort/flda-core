package com.github.tinosteinort.flda.accessor.writer;

import com.github.tinosteinort.flda.accessor.Attribute;

/**
 * Interface for a Writer. Implementations defines how a specific Attribute should be written.
 * @param <TUPEL_TYPE> The Type of the Data.
 * @param <ATTRIBUTE_TYPE> The Datatype of the Attribute.
 * @param <ATTRIBUTE_DESCRIPTION_TYPE> The Description of an Attribute.
 */
public interface AttributeWriter<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    /**
     * Writes a piece of data {@code value} into the Data'Row' {@code data}.
     * @param data The full Data'Row' which should be updated with the given value.
     * @param attribute The Description how the Data should be written.
     * @param value The Value to write.
     */
    void write(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute, ATTRIBUTE_TYPE value);
}
