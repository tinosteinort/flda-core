package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.tuple.IntegerTupleAttributeReader;
import com.github.tinosteinort.flda.accessor.tuple.StringTupleAttributeReader;
import com.github.tinosteinort.flda.accessor.tuple.StringTupleAttributeWriter;
import com.github.tinosteinort.flda.accessor.tuple.Tuple;
import com.github.tinosteinort.flda.accessor.tuple.TupleAttribute;
import com.github.tinosteinort.flda.accessor.tuple.TupleFactory;
import com.github.tinosteinort.flda.accessor.tuple.TupleSizeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccessorConfigTest {

    private final TupleAttribute<String> NAME = new TupleAttribute<>(String.class, 0);
    private final TupleAttribute<Integer> AGE = new TupleAttribute<>(Integer.class, 1);

    @Test public void newConfigNoReaders() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        assertEquals(0, config.readers().size());
    }

    @Test public void newConfigNoWriters() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        assertEquals(0, config.readers().size());
    }

    @Test public void newConfigNoRecordFactory() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        assertNull(config.recordFactory());
    }

    @Test(expected = RuntimeException.class)
    public void newConfigNoRecordFactoryButCreateRecord() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        config.createNewRecord();
    }

    @Test public void newConfigWithRecordFactory() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .withRecordFactory(new TupleFactory(3))
                .build();

        assertNotNull(config.recordFactory());
        assertNotNull(config.createNewRecord());
    }

    @Test public void newConfigNoReadValidator() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        assertNull(config.readValidator());
    }

    @Test public void newConfigWithReadValidator() {
        final TupleSizeValidator validator = new TupleSizeValidator(3);
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .withReadValidator(validator)
                .build();

        assertNull(config.writeValidator());
        assertNotNull(config.readValidator());
        assertEquals(validator, config.readValidator());
    }

    @Test public void newConfigNoWriteValidator() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        assertNull(config.writeValidator());
    }

    @Test public void newConfigWithWriteValidator() {
        final TupleSizeValidator validator = new TupleSizeValidator(3);
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .withWriteValidator(validator)
                .build();

        assertNull(config.readValidator());
        assertNotNull(config.writeValidator());
        assertEquals(validator, config.writeValidator());
    }

    @Test public void validateForReadRunsWithoutValidator() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        config.validateForRead(new Tuple(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateForReadWithValidatorAndExpectedError() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .withReadValidator(new TupleSizeValidator(4))
                .build();

        config.validateForRead(new Tuple(3));
    }

    @Test public void validateForWriteRunsWithoutValidator() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .build();

        config.validateForWrite(new Tuple(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateForWriteWithValidatorAndExpectedError() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .withWriteValidator(new TupleSizeValidator(4))
                .build();

        config.validateForWrite(new Tuple(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void readersAreUnmodifiableTestRemove() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, new StringTupleAttributeReader())
                .build();

        config.readers().entrySet().iterator().remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void readersAreUnmodifiableTestAdd() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, new StringTupleAttributeReader())
                .build();

        config.readers().put(Integer.class, new IntegerTupleAttributeReader());
    }

    @Test public void determineReaderForAttributeByClass() {
        final StringTupleAttributeReader reader = new StringTupleAttributeReader();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, reader)
                .build();

        final AttributeReader<Tuple, String, TupleAttribute<?>> resolvedReader = config.readerFor(NAME);
        assertNotNull(resolvedReader);
        assertEquals(reader, resolvedReader);
    }

    @Test public void determineWriterForAttributeByClass() {
        final StringTupleAttributeWriter writer = new StringTupleAttributeWriter();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerWriter(String.class, writer)
                .build();

        final AttributeWriter<Tuple, String, TupleAttribute<?>> resolvedWriter = config.writerFor(NAME);
        assertNotNull(resolvedWriter);
        assertEquals(writer, resolvedWriter);
    }

    @Test public void writerNotFound() {
        final StringTupleAttributeWriter writer = new StringTupleAttributeWriter();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerWriter(String.class, writer)
                .build();

        assertNull(config.writerFor(AGE));
    }

    @Test public void readerNotFound() {
        final IntegerTupleAttributeReader reader = new IntegerTupleAttributeReader();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(Integer.class, reader)
                .build();

        assertNull(config.readerFor(NAME));
    }

    @Test public void forAttributeRegisteredReaderOverwritesForClassRegisteredReader() {
        final StringTupleAttributeReader reader = new StringTupleAttributeReader();
        final StringTupleAttributeReader overwritingReader = new StringTupleAttributeReader();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, reader)
                .registerReader(NAME, overwritingReader)
                .build();

        final AttributeReader<Tuple, String, TupleAttribute<?>> resolvedReader = config.readerFor(NAME);
        assertNotNull(resolvedReader);
        assertEquals(overwritingReader, resolvedReader);
    }

    @Test public void forAttributeRegisteredWriterOverwritesForClassRegisteredWriter() {
        final StringTupleAttributeWriter writer = new StringTupleAttributeWriter();
        final StringTupleAttributeWriter overwritingWriter = new StringTupleAttributeWriter();
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerWriter(String.class, writer)
                .registerWriter(NAME, overwritingWriter)
                .build();

        final AttributeWriter<Tuple, String, TupleAttribute<?>> resolvedWriter = config.writerFor(NAME);
        assertNotNull(resolvedWriter);
        assertEquals(overwritingWriter, resolvedWriter);
    }

    @Test public void newReadAccessor() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, new StringTupleAttributeReader())
                .build();

        assertNotNull(config.newReadAccessor(new Tuple(5)));
    }

    @Test public void newWriteAccessor() {
        final AccessorConfig<Tuple, TupleAttribute<?>> config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerWriter(String.class, new StringTupleAttributeWriter())
                .build();

        assertNotNull(config.newWriteAccessor(new Tuple(5)));
    }
}
