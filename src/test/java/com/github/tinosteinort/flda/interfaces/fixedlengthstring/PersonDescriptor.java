package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

class PersonDescriptor {

    static final FixedLengthStringAttribute<String> FIRST_NAME = new FixedLengthStringAttribute<>(String.class, 0, 10);
    static final FixedLengthStringAttribute<String> LAST_NAME = new FixedLengthStringAttribute<>(String.class, 10, 10);
    static final FixedLengthStringAttribute<Integer> AGE = new FixedLengthStringAttribute<>(Integer.class, 20, 3);

    private PersonDescriptor() {

    }
}
