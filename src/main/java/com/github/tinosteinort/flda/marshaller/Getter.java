package com.github.tinosteinort.flda.marshaller;

public interface Getter<T, R> {
    R getValue(T obj);
}
