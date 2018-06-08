package com.github.tinosteinort.flda.marshaller;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.Attribute;
import com.github.tinosteinort.flda.accessor.ReadAccessor;
import com.github.tinosteinort.flda.accessor.WriteAccessor;

import java.util.ArrayList;
import java.util.List;

public class Marshaller<OBJECT_TYPE, TUPLE_TYPE, ATTRIBUTE_TYPE extends Attribute<?>> {

    private final Class<OBJECT_TYPE> object;
    private final AccessorConfig<TUPLE_TYPE, ATTRIBUTE_TYPE> accessorConfig;
    private final List<AccessorDefinition<?, ?, OBJECT_TYPE>> attributeMappings = new ArrayList<>();

    public Marshaller(final Class<OBJECT_TYPE> object,
                      final AccessorConfig<TUPLE_TYPE, ATTRIBUTE_TYPE> accessorConfig) {
        this.object = object;
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
        return marshall(obj, record);
    }

    public TUPLE_TYPE marshall(final OBJECT_TYPE obj, final TUPLE_TYPE record) {
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

