package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;

public class PersonFixedLengthStringReader {

    private final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> accessor;

    public PersonFixedLengthStringReader(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config,
            final FixedLengthString data) {
        this.accessor = new ReadAccessor<>(config, data);
    }

    public String firstname() {
        return accessor.read(PersonDescriptor.FIRST_NAME);
    }

    public String lastname() {
        return accessor.read(PersonDescriptor.LAST_NAME);
    }

    public Integer age() {
        return accessor.read(PersonDescriptor.AGE);
    }
}
