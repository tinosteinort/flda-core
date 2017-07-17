package com.github.tinosteinort.flda.interfaces.stringlist.reader;


import com.github.tinosteinort.flda.accessor.reader.AttributeReader;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;

import java.util.List;

public class IntegerAttributeReader implements AttributeReader<List<String>, Integer, StringListAttribute<Integer>> {

    @Override public Integer read(final List<String> record, final StringListAttribute<Integer> attribute) {
        return Integer.valueOf(record.get(attribute.getIndex()));
    }
}
