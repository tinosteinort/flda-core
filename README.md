# FLDA - Fixed Length Data Accessor

[![Maven Status](https://maven-badges.herokuapp.com/maven-central/com.github.tinosteinort/flda/badge.svg?style=flat)](http://mvnrepository.com/artifact/com.github.tinosteinort/flda)

FLDA provides mapping from either fixed-length or character-separated attributes to Java objects. The two-way mapping supports reading data as well as writing data. 

## Supported data formats
### Fixed-length attributes
Fixed-length data consists of attributes that each have a specific position and length. Hence, every n-th attribute begins at the same character-index. In this example, every name of an item begins at character-index 10 and every amount of items begins at character-index 16:  
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

### Example for Character-separated attributes
Character-separated data consists of attributes that are separated by a character. In this example, the attributes are semicolon-separated: 
```
Fruit;Cherry;30
Fruit;Apple;5
Vegetable;Potato;23
```

## Maven

To use FLDA, include the following artifact:
```xml
<dependency>
    <groupId>com.github.tinosteinort</groupId>
    <artifactId>flda</artifactId>
    <version>1.2.0</version>
</dependency>
```

## Examples
### Fixed-length attributes
FLDA supports a build-in `FixedLengthString`. E.g. it can be used if the data is stored line by line in a file. See [FixedLengthStringInterfaceTest](src/test/java/com/github/tinosteinort/flda/interfaces/fixedlengthstring/fullexample/FixedLengthStringInterfaceTest.java) for a full working example.

### Example File and Description
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

### Accessing the Data Record
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

### Record Factory
A record factory is used by `AccessorConfig.createNewRecord`. To use this
 method it is required to register a record factory:
```java
private final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            // ...
            .withRecordFactory(new FixedLengthStringFactory(23, ' '))
            .build();
```
The advantage of using a record factory is, that the code to create a new
 and empty record is in one place.
 
There are different predefined record factories:
* `FixedLengthString` => `FixedLengthStringFactory`
* `StringList` => `StringListFactory`

### Automatic Validation
It is possible to validate the records automatically. Just register the 
 desired validators:
```java
private final SizeValidator validator = new SizeValidator(InterfaceDescription.ATTRIBUTE_COUNT);

private final AccessorConfig<List<String>, StringListAttribute<?>> config =
        new AccessorConfigBuilder<List<String>, StringListAttribute<?>>()
                // ...
                .withReadValidator(validator)
                .withWriteValidator(validator)
                .build();
```
There are different predefined validators:
* `FixedLengthString` => `LengthValidator`
* `StringList` => `SizeValidator`

### Example for Character-separated attributes
See [StringListInterfaceTest](src/test/java/com/github/tinosteinort/flda/interfaces/stringlist/fullexample/StringListInterfaceTest.java) for a full working example. 

## Conclusion
FLDA can be used for many more data types than `FixedLenghtString`.
 With a new implementation of attributes, readers and writers, it will
 also work e.g. for `byte[]`.