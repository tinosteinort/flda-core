FLDA - Fixed Length Data Accessor
=================================

Library to support easy access to reading and writing data with fixed length.

# Introduction
FLDA eases the access of fixed length data. Fixed
 length data consists of data records that each have the same structure. 
 Every attribute has a specific position and length, or just an index
 within the data set. Therefore it does not matter if the record is 
 stored line by line in a file or if there are many records in a binary 
 file.

# Maven

Include the following artifact:
```xml
<dependency>
    <groupId>com.github.tinosteinort</groupId>
    <artifactId>flda</artifactId>
    <version>1.0.0</version>
</dependency>
```

# Example
FLDA supports a build-in `FixedLengthString`. E.g. it can be 
 used if the data is stored line by line in a file. See
 [FixedLengthStringInterfaceTest](src/test/java/com/github/tinosteinort/flda/interfaces/fixedlengthstring/fullexample/FixedLengthStringInterfaceTest.java)
 for a full working example.

## Example File and Description
The example file contains a list of fruits and vegetables, each having the attributes 
type, name and amount. One record has 17 characters.
 
Content of `Data.txt`:
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

A descriptor defines each attribute which should be read or written.
 In case of the `FixedLengthString`, an attribute needs a type, startIndex
 and length. With this information, all attributes can be accessed.
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
Before the data can be accessed, we need a configuration of how a
 type or attribute has to be handled.
```java
final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(DataDescriptor.AMOUNT, new IntegerAttributeWriter(StringFitter.Alignment.RIGHT, ' '))
            .build();
```
It is also possible to register readers and writers for attributes, instead of classes.
 Because of this, customization is possible.

Get the content of the File in `FixedLengthString`s:
```java
List<FixedLengthString> data = 
        Files.readAllLines(Paths.get("Data.txt"))
        .stream()
        .map(FixedLengthString::new)
        .collect(Collectors.toList());
```

Then, the FixedLengthStrings can be accessed e.g. with the `ReadAccessor`
```java
ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(config, data)
final String type = readAccessor.read(DataDescriptor.TYPE);
final String name = readAccessor.read(DataDescriptor.NAME);
final int amount = readAccessor.read(DataDescriptor.AMOUNT);
```

Writing to the file can be done using the `WriteAccessor`:
```java
FixedLengthString data = new FixedLengthString(17, ' ');
WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writeAccessor = new WriteAccessor<>(config, data)
readAccessor.write(DataDescriptor.TYPE, "Vegetable");
readAccessor.write(DataDescriptor.NAME, "Carrot");
readAccessor.write(DataDescriptor.AMOUNT, 5);
```

# Conclusion
FLDA can be used for many more data types than `FixedLenghtString`.
 With a new implementation of attributes, readers and writers, it will
 also work e.g. for `byte[]`.