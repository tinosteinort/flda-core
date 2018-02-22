package com.github.tinosteinort.flda.accessor;

import com.github.tinosteinort.flda.accessor.tupel.Tupel;
import com.github.tinosteinort.flda.accessor.tupel.TupelAttribute;
import com.github.tinosteinort.flda.accessor.tupel.TupelFactory;
import com.github.tinosteinort.flda.accessor.tupel.TupelSizeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AccessorConfigTest {


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

    // TODO readers() / writers() is unmodifiable
    // TODO writerFor(...) / readerFor(...)
}
