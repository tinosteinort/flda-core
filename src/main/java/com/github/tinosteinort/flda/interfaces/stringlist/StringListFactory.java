package com.github.tinosteinort.flda.interfaces.stringlist;

import com.github.tinosteinort.flda.accessor.AccessorConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * This factory create new instances of {@code List<String>}, filled with
 *  a specific number of {@code null} Elements. Instances of this class are used by
 *  {@link AccessorConfig#createNewRecord()} if the factory is registered
 *  with {@link com.github.tinosteinort.flda.accessor.AccessorConfigBuilder#withRecordFactory(Supplier)}.
 */
public class StringListFactory implements Supplier<List<String>> {

    private final int size;

    /**
     * @param size the exact size of the list.
     */
    public StringListFactory(final int size) {
        this.size = size;
    }

    /**
     * Creates a new instance of a {@code List<String>}.
     *
     * @return a new instance of a {@code List<String>} with the defined number of {@code null} elements.
     */
    @Override public List<String> get() {
        final List<String> record = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            record.add(null);
        }
        return record;
    }
}
