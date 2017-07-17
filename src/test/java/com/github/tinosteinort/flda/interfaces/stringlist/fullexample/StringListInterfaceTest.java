package com.github.tinosteinort.flda.interfaces.stringlist.fullexample;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
import com.github.tinosteinort.flda.interfaces.stringlist.StringListAttribute;
import com.github.tinosteinort.flda.interfaces.stringlist.reader.IntegerAttributeReader;
import com.github.tinosteinort.flda.interfaces.stringlist.reader.StringAttributeReader;
import com.github.tinosteinort.flda.interfaces.stringlist.writer.IntegerAttributeWriter;
import com.github.tinosteinort.flda.interfaces.stringlist.writer.StringAttributeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO Convert this class to "real" JUnit Test with same structure and naming as FixedLengthStringInterfaceTest.
public class StringListInterfaceTest {

    private final AccessorConfig<List<String>, StringListAttribute<?>> config =
            new AccessorConfigBuilder<List<String>, StringListAttribute<?>>()
                    .registerReader(String.class, new StringAttributeReader())
                    .registerReader(Integer.class, new IntegerAttributeReader())
                    .registerWriter(String.class, new StringAttributeWriter())
                    .registerWriter(Integer.class, new IntegerAttributeWriter())
                    .build();

    public static void main(String[] args) throws IOException {
        new StringListInterfaceTest().run();
    }

    private void run() throws IOException {

        readExample();
        writeExample();
    }

    private void readExample() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("Data.txt").getFile());
        final List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {

            final List<String> record = Arrays.asList(line.split(";"));

            // Here, you could simply filter which lines are actually mapped by FLDA.

            final ReadAccessor<List<String>, StringListAttribute<?>> readAccessor =
                    new ReadAccessor<>(config, record);

            System.out.println(readAccessor.read(InterfaceDescription.FIRSTNAME));
            System.out.println(readAccessor.read(InterfaceDescription.LASTNAME));
            System.out.println(readAccessor.read(InterfaceDescription.AGE));
            System.out.println("------");
        }
    }

    private void writeExample() {

        // TODO It's pretty ugly to add nulls to set the size of this list. Find another way to do
        // record.set(index, value) safely.

        final List<String> record = new ArrayList<>(InterfaceDescription.ATTRIBUTE_COUNT);
        record.add(null);
        record.add(null);
        record.add(null);

        final WriteAccessor<List<String>, StringListAttribute<?>> writeAccessor =
                new WriteAccessor<>(config, record);

        writeAccessor.write(InterfaceDescription.FIRSTNAME, "Bimmel");
        writeAccessor.write(InterfaceDescription.LASTNAME, "Bammel");
        writeAccessor.write(InterfaceDescription.AGE, 500);

        final String recordAsString = record
                .stream()
                .collect(Collectors.joining(";"));
        System.out.println(recordAsString);
    }
}
