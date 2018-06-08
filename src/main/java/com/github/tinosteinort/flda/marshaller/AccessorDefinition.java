package com.github.tinosteinort.flda.marshaller;

import com.github.tinosteinort.flda.accessor.Attribute;

class AccessorDefinition<ATTRIBUTE_TYPE extends Attribute<VALUE_TYPE>, VALUE_TYPE, OBJECT_TYPE> {

    private final ATTRIBUTE_TYPE attribute;
    private final Getter<OBJECT_TYPE, VALUE_TYPE> getter;
    private final Setter<OBJECT_TYPE, VALUE_TYPE> setter;

    public AccessorDefinition(ATTRIBUTE_TYPE attribute,
                              final Getter<OBJECT_TYPE, VALUE_TYPE> getter,
                              final Setter<OBJECT_TYPE, VALUE_TYPE> setter) {
        this.attribute = attribute;
        this.getter = getter;
        this.setter = setter;
    }

    public ATTRIBUTE_TYPE getAttribute() {
        return attribute;
    }

    public Getter<OBJECT_TYPE, VALUE_TYPE> getGetter() {
        return getter;
    }

    public Setter<OBJECT_TYPE, VALUE_TYPE> getSetter() {
        return setter;
    }
}
