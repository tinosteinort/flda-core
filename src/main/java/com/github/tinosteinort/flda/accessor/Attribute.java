package com.github.tinosteinort.flda.accessor;

/**
 * Describes the Structure of an Attribute. E.g an Startindex + length or just an Index.
 *
 * @param <T> The Datatype of the Attribute
 */
public interface Attribute<T> {

    Class<T> getType();
}
