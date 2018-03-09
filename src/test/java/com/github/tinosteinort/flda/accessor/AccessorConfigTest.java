package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.tupel.IntegerTupelAttributeReader;
import com.github.tinosteinort.flda.accessor.tupel.StringTupelAttributeReader;
import com.github.tinosteinort.flda.accessor.tupel.StringTupelAttributeWriter;
import com.github.tinosteinort.flda.accessor.tupel.Tupel;
import com.github.tinosteinort.flda.accessor.tupel.TupelAttribute;
import com.github.tinosteinort.flda.accessor.tupel.TupelFactory;
import com.github.tinosteinort.flda.accessor.tupel.TupelSizeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccessorConfigTest {

    private final TupelAttribute<String> NAME = new TupelAttribute<>(String.class, 0);
    private final TupelAttribute<Integer> AGE = new TupelAttribute<>(Integer.class, 1);

    @Test public void newConfigNoReaders() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        assertEquals(0, config.readers().size());
    }

    @Test public void newConfigNoWriters() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        assertEquals(0, config.readers().size());
    }

    @Test public void newConfigNoRecordFactory() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        assertNull(config.recordFactory());
    }

    @Test(expected = RuntimeException.class)
    public void newConfigNoRecordFactoryButCreateRecord() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        config.createNewRecord();
    }

    @Test public void newConfigWithRecordFactory() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .withRecordFactory(new TupelFactory(3))
                .build();

        assertNotNull(config.recordFactory());
        assertNotNull(config.createNewRecord());
    }

    @Test public void newConfigNoReadValidator() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        assertNull(config.readValidator());
    }

    @Test public void newConfigWithReadValidator() {
        final TupelSizeValidator validator = new TupelSizeValidator(3);
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .withReadValidator(validator)
                .build();

        assertNull(config.writeValidator());
        assertNotNull(config.readValidator());
        assertEquals(validator, config.readValidator());
    }

    @Test public void newConfigNoWriteValidator() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        assertNull(config.writeValidator());
    }

    @Test public void newConfigWithWriteValidator() {
        final TupelSizeValidator validator = new TupelSizeValidator(3);
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .withWriteValidator(validator)
                .build();

        assertNull(config.readValidator());
        assertNotNull(config.writeValidator());
        assertEquals(validator, config.writeValidator());
    }

    @Test public void validateForReadRunsWithoutValidator() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        config.validateForRead(new Tupel(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateForReadWithValidatorAndExpectedError() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .withReadValidator(new TupelSizeValidator(4))
                .build();

        config.validateForRead(new Tupel(3));
    }

    @Test public void validateForWriteRunsWithoutValidator() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .build();

        config.validateForWrite(new Tupel(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateForWriteWithValidatorAndExpectedError() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .withWriteValidator(new TupelSizeValidator(4))
                .build();

        config.validateForWrite(new Tupel(3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void readersAreUnmodifiableTestRemove() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerReader(String.class, new StringTupelAttributeReader())
                .build();

        config.readers().entrySet().iterator().remove();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void readersAreUnmodifiableTestAdd() {
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerReader(String.class, new StringTupelAttributeReader())
                .build();

        config.readers().put(Integer.class, new IntegerTupelAttributeReader());
    }

    @Test public void determineReaderForAttributeByClass() {
        final StringTupelAttributeReader reader = new StringTupelAttributeReader();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerReader(String.class, reader)
                .build();

        final AttributeReader<Tupel, String, TupelAttribute<?>> resolvedReader = config.readerFor(NAME);
        assertNotNull(resolvedReader);
        assertEquals(reader, resolvedReader);
    }

    @Test public void determineWriterForAttributeByClass() {
        final StringTupelAttributeWriter writer = new StringTupelAttributeWriter();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerWriter(String.class, writer)
                .build();

        final AttributeWriter<Tupel, String, TupelAttribute<?>> resolvedWriter = config.writerFor(NAME);
        assertNotNull(resolvedWriter);
        assertEquals(writer, resolvedWriter);
    }

    @Test public void writerNotFound() {
        final StringTupelAttributeWriter writer = new StringTupelAttributeWriter();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerWriter(String.class, writer)
                .build();

        assertNull(config.writerFor(AGE));
    }

    @Test public void readerNotFound() {
        final IntegerTupelAttributeReader reader = new IntegerTupelAttributeReader();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerReader(Integer.class, reader)
                .build();

        assertNull(config.readerFor(NAME));
    }

    @Test public void forAttributeRegisteredReaderOverwritesForClassRegisteredReader() {
        final StringTupelAttributeReader reader = new StringTupelAttributeReader();
        final StringTupelAttributeReader overwritingReader = new StringTupelAttributeReader();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerReader(String.class, reader)
                .registerReader(NAME, overwritingReader)
                .build();

        final AttributeReader<Tupel, String, TupelAttribute<?>> resolvedReader = config.readerFor(NAME);
        assertNotNull(resolvedReader);
        assertEquals(overwritingReader, resolvedReader);
    }

    @Test public void forAttributeRegisteredWriterOverwritesForClassRegisteredWriter() {
        final StringTupelAttributeWriter writer = new StringTupelAttributeWriter();
        final StringTupelAttributeWriter overwritingWriter = new StringTupelAttributeWriter();
        final AccessorConfig<Tupel, TupelAttribute<?>> config = new AccessorConfigBuilder<Tupel, TupelAttribute<?>>()
                .registerWriter(String.class, writer)
                .registerWriter(NAME, overwritingWriter)
                .build();

        final AttributeWriter<Tupel, String, TupelAttribute<?>> resolvedWriter = config.writerFor(NAME);
        assertNotNull(resolvedWriter);
        assertEquals(overwritingWriter, resolvedWriter);
    }
}
