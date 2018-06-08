package com.github.tinosteinort.flda.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Marshaller<OBJECT_TYPE, TUPLE_TYPE, ATTRIBUTE_TYPE extends Attribute<?>> {

    private final Class<OBJECT_TYPE> object;
    private final AccessorConfig<TUPLE_TYPE, ATTRIBUTE_TYPE> accessorConfig;
    private final List<AccessorDefinition<?, ?, OBJECT_TYPE>> attributeMappings = new ArrayList<>();

    public Marshaller(final Class<OBJECT_TYPE> object,
                      final AccessorConfig<TUPLE_TYPE, ATTRIBUTE_TYPE> accessorConfig) {
        this.object = object;
        Objects.requireNonNull(accessorConfig.recordFactory(), "recordFactory required");
        this.accessorConfig = accessorConfig;
    }

    public <T, ATTR extends Attribute<T>> void withAttribute(ATTR attributeType,
                                                             final Getter<OBJECT_TYPE, T> getter,
                                                             final Setter<OBJECT_TYPE, T> setter) {
        AccessorDefinition<ATTR, T, OBJECT_TYPE> def = new AccessorDefinition<>(attributeType, getter, setter);
        attributeMappings.add(def);
    }

    public TUPLE_TYPE marshall(final OBJECT_TYPE obj) {
        final TUPLE_TYPE record = accessorConfig.createNewRecord();

        final WriteAccessor<TUPLE_TYPE, ATTRIBUTE_TYPE> accessor = accessorConfig.newWriteAccessor(record);

        for (AccessorDefinition<?, ?, OBJECT_TYPE> def : attributeMappings) {
            write(def, obj, accessor);
        }

        return record;
    }

    public OBJECT_TYPE unmarshall(final TUPLE_TYPE record, final OBJECT_TYPE target) {

        final ReadAccessor<TUPLE_TYPE, ATTRIBUTE_TYPE> accessor = accessorConfig.newReadAccessor(record);

        for (AccessorDefinition<?, ?, OBJECT_TYPE> def : attributeMappings) {
            read(def, target, accessor);
        }

        return target;
    }

    private <ATTR extends Attribute<T>, T> void write(final AccessorDefinition<ATTR, T, OBJECT_TYPE> def,
                                                      final OBJECT_TYPE obj,
                                                      final WriteAccessor<TUPLE_TYPE, ATTRIBUTE_TYPE> accessor) {

        final T value = def.getGetter().getValue(obj);
        accessor.write(def.getAttribute(), value);
    }

    private <ATTR extends Attribute<T>, T> void read(final AccessorDefinition<ATTR, T, OBJECT_TYPE> def,
                                                      final OBJECT_TYPE obj,
                                                      final ReadAccessor<TUPLE_TYPE, ATTRIBUTE_TYPE> accessor) {

        final T value = accessor.read(def.getAttribute());
        def.getSetter().setValue(obj, value);
    }
}

interface Getter<T, R> {
    R getValue(T obj);
}
interface Setter<T, R> {
    void setValue(T obj, R value);
}

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