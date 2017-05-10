package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.reader.ReadAccessor;
import com.github.tinosteinort.flda.accessor.writer.WriteAccessor;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader.IntegerAttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.reader.StringAttributeReader;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer.IntegerAttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer.RightAlignedIntegerAttributeWriter;
import com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer.StringAttributeWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class TestFixedLenghtStringInterface {

    private final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
            .build();

    @Test public void testImport() throws UnsupportedEncodingException {

        final String dataFromFile = "Dagobert  Duck      100";

        final Person person = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(100, person.getAge());
    }

    public Person doImport(final String data) {

        final Person obj = new Person();

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(config, new FixedLengthString(data));

        reader.firstname().ifPresent(obj::setFirstname);
        reader.lastname().ifPresent(obj::setLastname);
        reader.age().ifPresent(obj::setAge);

        return obj;
    }

    @Test public void testExport() throws UnsupportedEncodingException {

        final Person obj = new Person();
        obj.setFirstname("Dagobert");
        obj.setLastname("Duck");
        obj.setAge(100);

        Assert.assertEquals(
                new FixedLengthString("Dagobert  Duck      100"),
                doExport(obj));
    }

    public FixedLengthString doExport(final Person obj) {

        final FixedLengthString row = new FixedLengthString(23, ' ');

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(config, row);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return row;
    }

    @Test public void testImportCustomAttribute() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
                // not needed, because trim() removes spaces from left and right:
                // .registerReader(PersonDescriptor.AGE, new RightAlignedIntegerAttributeReader())
                .build();

        // Age is right aligned, custom AttributeReader required for reading
        final String dataFromFile = "Tick      Duck        7";

        final Person person = new Person();

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(localConfig, new FixedLengthString(dataFromFile));

        reader.firstname().ifPresent(person::setFirstname);
        reader.lastname().ifPresent(person::setLastname);
        reader.age().ifPresent(person::setAge);

        Assert.assertEquals("Tick", person.getFirstname());
        Assert.assertEquals("Duck", person.getLastname());
        Assert.assertEquals(7, person.getAge());
    }

    @Test public void testExportCustomAttribute() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
                .registerWriter(PersonDescriptor.AGE, new RightAlignedIntegerAttributeWriter()) // override default behaviour for special Attribute
                .build();

        final Person obj = new Person();
        obj.setFirstname("Tick");
        obj.setLastname("Duck");
        obj.setAge(7); // Age should be right aligned, custom AttributeWriter required for writing

        final FixedLengthString row = new FixedLengthString(23, ' ');

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(localConfig, row);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        Assert.assertEquals(
                new FixedLengthString("Tick      Duck        7"),
                row);
    }

    @Test public void genericParameterTest() {

        final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> localConfig = new AccessorConfigBuilder<>(config)
                .registerReader(String.class, new StringAttributeReader())
                .registerReader(Integer.class, new IntegerAttributeReader())
                .registerWriter(String.class, new StringAttributeWriter())
                .registerWriter(Integer.class, new IntegerAttributeWriter())
                .build();

        final String data = "Tick      Duck        7";

        final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> reader = new ReadAccessor<>(localConfig, new FixedLengthString(data));
        final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writer = new WriteAccessor<>(localConfig, new FixedLengthString(data));
        // This two Lines of Code does not compile properly if everything works fine
//        final String age = reader.read(PersonDescriptor.AGE);
//        writer.write(PersonDescriptor.AGE, "123");
    }
}
