package com.github.tinosteinort.flda.accessor;

public interface RecordValidator<RECORD_TYPE> {

    void validate(RECORD_TYPE record);
}
