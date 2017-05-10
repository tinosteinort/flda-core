package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;

import java.util.Optional;

public class PersonFixedLengthStringReader {

    private final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> accessor;

    public PersonFixedLengthStringReader(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config,
            final FixedLengthString data) {
        this.accessor = new ReadAccessor<>(config, data);
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.AGE));
    }
}
