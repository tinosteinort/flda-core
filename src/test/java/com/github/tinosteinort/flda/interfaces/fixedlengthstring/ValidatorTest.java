package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ValidatorTest {

    private final LengthValidator readValidator = new LengthValidator(5);
    private final LengthValidator writeValidator = new LengthValidator(5);

    private final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config =
            new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
                    .withReadValidator(readValidator)
                    .withWriteValidator(writeValidator)
                    .build();

    @Test public void validateConfigRead() {
        assertEquals(readValidator, config.readValidator());
    }

    @Test public void validateConfigWrite() {
        assertEquals(writeValidator, config.writeValidator());
    }

    @Test public void validateDifferentReaders() {
        assertNotEquals(config.readValidator(), config.writeValidator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRead() {

        final FixedLengthString data = new FixedLengthString(4, ' ');
        new ReadAccessor<>(config, data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateWrite() {

        final FixedLengthString data = new FixedLengthString(6, ' ');
        new WriteAccessor<>(config, data);
    }

    @Test public void nullValidators() {
        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config =
                new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
                        .withReadValidator(null)
                        .withWriteValidator(null)
                        .build();

        final FixedLengthString data = new FixedLengthString(5, ' ');

        new ReadAccessor<>(config, data);
        new WriteAccessor<>(config, data);
    }
}
