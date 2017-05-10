package com.github.tinosteinort.flda.accessor.writer;

import com.github.tinosteinort.flda.accessor.Attribute;

public interface AttributeWriter<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    void write(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute, ATTRIBUTE_TYPE value);
}
