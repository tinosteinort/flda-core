package com.github.tinosteinort.flda.marshaller;

public interface Setter<T, R> {
    void setValue(T obj, R value);
}
