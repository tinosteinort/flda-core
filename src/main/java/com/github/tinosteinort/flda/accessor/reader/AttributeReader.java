package com.github.tinosteinort.flda.accessor.reader;

import com.github.tinosteinort.flda.accessor.Attribute;

public interface AttributeReader<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    ATTRIBUTE_TYPE read(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute);
}
