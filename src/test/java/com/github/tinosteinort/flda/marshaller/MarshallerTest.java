package com.github.tinosteinort.flda.marshaller;

import com.github.tinosteinort.flda.accessor.AccessorConfig;
import com.github.tinosteinort.flda.accessor.AccessorConfigBuilder;
import com.github.tinosteinort.flda.accessor.tuple.IntegerTupleAttributeReader;
import com.github.tinosteinort.flda.accessor.tuple.IntegerTupleAttributeWriter;
import com.github.tinosteinort.flda.accessor.tuple.StringTupleAttributeReader;
import com.github.tinosteinort.flda.accessor.tuple.StringTupleAttributeWriter;
import com.github.tinosteinort.flda.accessor.tuple.Tuple;
import com.github.tinosteinort.flda.accessor.tuple.TupleAttribute;
import com.github.tinosteinort.flda.accessor.tuple.TupleFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarshallerTest {

    private final TupleAttribute<String> NAME = new TupleAttribute<>(String.class, 0);
    private final TupleAttribute<Integer> AGE = new TupleAttribute<>(Integer.class, 1);

    private AccessorConfig<Tuple, TupleAttribute<?>> config;
    private Marshaller<Person, Tuple, TupleAttribute<?>> marshaller;

    @Before public void setup() {

        config = new AccessorConfigBuilder<Tuple, TupleAttribute<?>>()
                .registerReader(String.class, new StringTupleAttributeReader())
                .registerWriter(String.class, new StringTupleAttributeWriter())
                .registerReader(Integer.class, new IntegerTupleAttributeReader())
                .registerWriter(Integer.class, new IntegerTupleAttributeWriter())
                .withRecordFactory(new TupleFactory(2))
                .build();

        marshaller = new Marshaller<>(Person.class, config);
        marshaller.withAttribute(NAME, Person::getName, Person::setName);
        marshaller.withAttribute(AGE, Person::getAge, Person::setAge);
    }

    @Test public void marshall() {

        Person person = new Person("Neo", 123);
        Tuple tuple = marshaller.marshall(person);

        assertNotNull(tuple);
        assertEquals("Neo", tuple.get(0));
        assertEquals(123, tuple.get(1));
    }

    @Test public void unmarshall() {

        Tuple tuple = new Tuple("Neo", 456);
        Person newPerson = marshaller.unmarshall(tuple, new Person());

        assertNotNull(newPerson);
        assertEquals("Neo", newPerson.getName());
        assertEquals(456, newPerson.getAge());
    }
}


class Person {

    private String name;
    private int age;

    public Person() {

    }

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}