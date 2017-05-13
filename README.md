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

# Example
In Library supports a build in `FixedLengthString`. E.g. it can be 
 used if the Data is stored Line by Line in a File.

## Example File and Description
Our File contains a List of Fruits and Vegetable. The Attributes are:
 Type, Name and Amount. One Record has 17 Characters
 
Content of the `Data.txt`:
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

A Descriptor describes each Attribute which should be read or written.
 In Case of the `FixedLengthString`, an Attributes needs a Type, StartIndex
 and Length. With this Information, all Attributes can be accessed.
```
class DataDescriptor {

    static final FixedLengthStringAttribute<String> TYPE = new FixedLengthStringAttribute<>(String.class, 0, 10);
    static final FixedLengthStringAttribute<String> NAME = new FixedLengthStringAttribute<>(String.class, 10, 10);
    static final FixedLengthStringAttribute<Integer> AMOUNT = new FixedLengthStringAttribute<>(Integer.class, 20, 3);

    private PersonDescriptor() {

    }
}
```

## Accessing the Data Record
Before the Data can be accessed, we need a Configuration of how a
 Type or Attribute has to be handled.
```
final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
            .build();
```

Get the Content of the File in `FixedLengthString`s:
```
List<FixedLengthString> data = 
        Files.readAllLines(Paths.get("Data.txt"))
        .stream()
        .map(FixedLengthString::new)
        .collect(Collectors.toList());
```

Than, the FixedLengthStrings can be accessed e.g. with the `ReadAccessor`
```
ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(config, data)
final String type = readAccessor.read(DataDescriptor.TYPE);
final String name = readAccessor.read(DataDescriptor.NAME);
final int amount = readAccessor.read(DataDescriptor.AMOUNT);
```

The other way is to write:
```
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