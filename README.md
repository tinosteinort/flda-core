# FLDA - Fixed Length Data Accessor

[![Maven Status](https://maven-badges.herokuapp.com/maven-central/com.github.tinosteinort/flda/badge.svg?style=flat)](http://mvnrepository.com/artifact/com.github.tinosteinort/flda)

FLDA supports reading data as well as writing data of records with a fix size.

Fix size means:
 * each record has *n* Attributes. An Attribute has an index.
 * **or:** each record has a fix length. An Attribute has an index and a length. 

This project is only the *core*. For some usages of this project see:
* [flda-fixedlengthstring](https://github.com/tinosteinort/flda-fixedlengthstring)
* [flda-stringlist](https://github.com/tinosteinort/flda-stringlist)

## Examples of different record types
### Fixed-length - `FixedLengthString`
Fixed-length data consists of attributes that each have a specific position and length. Hence, every n-th attribute begins at the same character-index. In this example, every name of an item begins at character-index 10 and every amount of items begins at character-index 16:  
```
Fruit    Cherry30
Fruit    Apple  5
VegetablePotato23
```

### Fix amount of Attributes - `List<String>`
Character-separated data consists of attributes that are separated by a character. In this example, the attributes
 are semicolon-separated and each record consists of three Attributes. The record type is `List<String>`.
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
    <artifactId>flda-core</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Short Description of the Accessor Concept

Within the Accessor Concept there are different parts:
1. the fixed size data record
2. the definition of an attribute
3. an accessor

#### 1. Fixed Size Data Record
This is just the part of data to read from or to write to.

#### 2. Definition of an attribute
This is the information how an attribute is addressed. There are different questions to answer:
* of which **type** is the attribute?
* on which **index** is the attribute located?
* **how long** (e.g. in  characters or bytes) is the attribute?

> For some record types not all questions are relevant. For an index based record type, there is no need to know
 the size of one attribute.

Example of a `FixedLengthString` attribute with index and length:
```java
FixedLengthStringAttribute<String> FIRST_NAME = new FixedLengthStringAttribute<>(String.class, 0, 10);
```

#### 3. The Accessor
A `ReadAccessor` reads from a record and a `WriteAccessor` writes to the record. Therefore an attribute definition
 is needed. An `AccessorConfig` is needed too.

Read Example:
```java
AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = ...

ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> readAccessor = new ReadAccessor<>(config, datarecord)
String firstname = readAccessor.read(FIRST_NAME)
```

Example for write a record:
```java
AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = ...

WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> writeAccessor = new WriteAccessor<>(config, record);
writeAccessor.write(PersonDescriptor.FIRST_NAME, person.getFirstname());
```



## Record Factory
A record factory is used by `AccessorConfig.createNewRecord`. To use this
 method it is required to register a record factory:
```java
AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
        // ...
        .withRecordFactory(new FixedLengthStringFactory(23, ' '))
        .build();
```
The advantage of using a record factory is, that the code to create a new record is in one place.
 
There are different predefined record factories:
* `FixedLengthString` => `FixedLengthStringFactory`
* `StringList` => `StringListFactory`

## Automatic Validation
It is possible to validate the records automatically. Just register the  desired validators:
```java
SizeValidator validator = new SizeValidator(5);

AccessorConfig<List<String>, StringListAttribute<?>> config =
        new AccessorConfigBuilder<List<String>, StringListAttribute<?>>()
                // ...
                .withReadValidator(validator)
                .withWriteValidator(validator)
                .build();
```
There are different predefined validators:
* `FixedLengthString` => `LengthValidator`
* `StringList` => `SizeValidator`


# Change History

#### Migration to version 2.0
* Move `StringFitter` from
    `com.github.tinosteinort.flda.interfaces.fixedlengthstring.writer`
    to
    `com.github.tinosteinort.flda.interfaces.fixedlengthstring`
* `StringFitter` is renamed to `StringUtils`
* Extract `StringFitter.Alignment` into own Class: 
 `com.github.tinosteinort.flda.interfaces.fixedlengthstring.Alignment`
* Remove `Alignment` Class from FixedLengthString Writer Classes into
 `com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute`
* Remove `filler` char from FixedLengthString Writer Classes into
 `com.github.tinosteinort.flda.interfaces.fixedlengthstring.FixedLengthStringAttribute`
* Method `FixedLengthString#getString()` replaced by `FixedLengthString#toString()`
    * Output of old `FixedLengthString#toString()` Method is not supported anymore
* Class `StringReader` is replaced by Method `StringUtils#readAndTrim()`
* `FixedLenghtString` implements `CharSequence`
* API Update: move filler char and alignment into `FixedLengthStringAttribute` 
* split project `flda` in `flda-core`,  `flda-fixedlengthstring` and  `flda-stringlist`
* introduce own `RecordFactory<T>` interface. `Supplier<T>` was used before
* change `AccessorConfig` from interface to abstract class. Needed to clean up the
 API