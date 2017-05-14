FLDA - Fixed Length Data Accessor
=================================

Library to support easy Access to read and write Fixed Length Data.


# Introduction
This Library should ease the Access of Fixed Length Data. Fixed
 Length Data is when a Datarecord has a fix Structure. Every Attribute
 has a specific Position and Length, or just an Index. Therefore it
 does not matter if the Record is stored Line by Line in a File
 or if there are many Records in a binary File. If the Record has
 a fix Length, and so the Attributes, this Library should help to
 Access the Data of the Record.

# Maven
INFO: Not yet available in Maven Central, WIP

Include the following Artifact to use the `BeanRepository`:
```xml
<dependency>
    <groupId>com.github.tinosteinort</groupId>
    <artifactId>flda</artifactId>
    <version>0.0.1</version>
</dependency>
```

# Example
In Library supports a build in `FixedLengthString`. E.g. it can be 
 used if the Data is stored Line by Line in a File. See
 [FixedLengthStringInterfaceTest](src/test/java/com/github/tinosteinort/flda/interfaces/fixedlengthstring/fullexample/FixedLengthStringInterfaceTest.java)
 for a full working Example.

## Example File and Description
Our File contains a List of Fruits and Vegetable. The Attributes are:
 Type, Name and Amount. One Record has 17 Characters.
 
Content of the `Data.txt`:
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

A Descriptor describes each Attribute which should be read or written.
 In Case of the `FixedLengthString`, an Attributes needs a Type, StartIndex
 and Length. With this Information, all Attributes can be accessed.
```java
class DataDescriptor {

    static final FixedLengthStringAttribute<String> TYPE = new FixedLengthStringAttribute<>(String.class, 0, 9);
    static final FixedLengthStringAttribute<String> NAME = new FixedLengthStringAttribute<>(String.class, 9, 6);
    static final FixedLengthStringAttribute<Integer> AMOUNT = new FixedLengthStringAttribute<>(Integer.class, 15, 2);

    private PersonDescriptor() {

    }
}
```

## Accessing the Data Record
Before the Data can be accessed, we need a Configuration of how a
 Type or Attribute has to be handled.
```java
final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
            .registerWriter(DataDescriptor.AMOUNT, new RightAlignedIntegerAttributeReader())
            .build();
```
It is also possible to register Readers and Writers for Atributes, instead of Classes.
 Because of this, Customization is possible.

Get the Content of the File in `FixedLengthString`s:
```java
List<FixedLengthString> data = 
        Files.readAllLines(Paths.get("Data.txt"))
        .stream()
        .map(FixedLengthString::new)
        .collect(Collectors.toList());
```

Than, the FixedLengthStrings can be accessed e.g. with the `ReadAccessor`
```java
ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(config, data)
final String type = readAccessor.read(DataDescriptor.TYPE);
final String name = readAccessor.read(DataDescriptor.NAME);
final int amount = readAccessor.read(DataDescriptor.AMOUNT);
```

The other way is to write:
```java
FixedLengthString data = new FixedLengthString(17, ' ');
WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writeAccessor = new WriteAccessor<>(config, data)
readAccessor.write(DataDescriptor.TYPE, "Vegetable");
readAccessor.write(DataDescriptor.NAME, "Carrot");
readAccessor.write(DataDescriptor.AMOUNT, 5);
```

# Conclusion
This Library can be used for many more DataTypes than `FixedLenghtString`.
 With a new Implementation of Attributes and Readers and Writers, it will
 also work e.g. for `byte[]`. 