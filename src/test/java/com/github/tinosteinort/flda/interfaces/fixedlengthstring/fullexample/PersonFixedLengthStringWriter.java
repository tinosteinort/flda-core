package com.github.tinosteinort.flda.interfaces.fixedlengthstring.fullexample;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthString;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class PersonFixedLengthStringWriter {

    private final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> accessor;

    public PersonFixedLengthStringWriter(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config,
            final FixedLengthString data) {
        this.accessor = new WriteAccessor<>(config, data);
    }

    public void firstname(final String firstname) {
        accessor.write(PersonDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        accessor.write(PersonDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        accessor.write(PersonDescriptor.AGE, age);
    }
}
