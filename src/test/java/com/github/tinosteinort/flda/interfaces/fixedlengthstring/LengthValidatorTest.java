package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import org.junit.Test;

public class LengthValidatorTest {

    @Test public void valid() {
        final LengthValidator validator = new LengthValidator(5);

        final FixedLengthString data = new FixedLengthString(5, '#');

        validator.validate(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooSmall() {
        final LengthValidator validator = new LengthValidator(5);

        final FixedLengthString data = new FixedLengthString(4, '#');

        validator.validate(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBig() {
        final LengthValidator validator = new LengthValidator(5);

        final FixedLengthString data = new FixedLengthString(6, '#');

        validator.validate(data);
    }

    @Test(expected = NullPointerException.class)
    public void recordIsNull() {
        final LengthValidator validator = new LengthValidator(3);

        validator.validate(null);
    }
}
