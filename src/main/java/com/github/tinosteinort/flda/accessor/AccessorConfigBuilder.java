package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.accessor.writer.AttributeWriter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    private final Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
    private final Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
    private final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
    private final Map<ATTRIBUTE_DESCRIPTION_TYPE, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();

    public AccessorConfigBuilder() {

    }

    public AccessorConfigBuilder(final AccessorConfig<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> baseConfig) {
        readersByType.putAll(baseConfig.readers());
        writersByType.putAll(baseConfig.writers());
    }

    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final Class<T> type,
            final AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> reader) {
        readersByType.put(type, reader);
        return this;
    }
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final Class<T> type,
            final AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writer) {
        writersByType.put(type, writer);
        return this;
    }

    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> reader) {
        readersByAttribute.put(attribute, reader);
        return this;
    }
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final ATTRIBUTE_DESCRIPTION_TYPE attribute,
            final AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writer) {
        writersByAttribute.put(attribute, writer);
        return this;
    }

    public AccessorConfig<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> build() {
        return new AccessorConfigImpl<>(readersByType, writersByType, readersByAttribute, writersByAttribute);
    }

    private class AccessorConfigImpl<TYPE, ATTR_DESC_TYPE extends Attribute<?>> implements AccessorConfig<TYPE, ATTR_DESC_TYPE> {

        private final Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByType = new HashMap<>();
        private final Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByType = new HashMap<>();
        private final Map<ATTR_DESC_TYPE, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByAttribute = new HashMap<>();
        private final Map<ATTR_DESC_TYPE, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByAttribute = new HashMap<>();

        private AccessorConfigImpl(
                final Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByType,
                final Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByType,
                final Map<ATTR_DESC_TYPE, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readersByAttribute,
                final Map<ATTR_DESC_TYPE, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writersByAttribute) {
            this.readersByType.putAll(readersByType);
            this.writersByType.putAll(writersByType);
            this.readersByAttribute.putAll(readersByAttribute);
            this.writersByAttribute.putAll(writersByAttribute);
        }

        @Override public <ATTR_TYPE> AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE> readerFor(final ATTR_DESC_TYPE attribute) {
            final AttributeReader<TYPE, ?, ? extends Attribute<?>> readerByAttribute = readersByAttribute.get(attribute);
            if (readerByAttribute == null) {
                return (AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readersByType.get(attribute.getType());
            }
            return (AttributeReader<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) readerByAttribute;
        }

        @Override public <ATTR_TYPE> AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE> writerFor(final ATTR_DESC_TYPE attribute) {
            final AttributeWriter<TYPE, ?, ? extends Attribute<?>> writerByAttribute = writersByAttribute.get(attribute);
            if (writerByAttribute == null) {
                return (AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writersByType.get(attribute.getType());
            }
            return (AttributeWriter<TYPE, ATTR_TYPE, ATTR_DESC_TYPE>) writerByAttribute;
        }

        public Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readers() {
            return Collections.unmodifiableMap(readersByType);
        }

        public Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writers() {
            return Collections.unmodifiableMap(writersByType);
        }
    }
}
