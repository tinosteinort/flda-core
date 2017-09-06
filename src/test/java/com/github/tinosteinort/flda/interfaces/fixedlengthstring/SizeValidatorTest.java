package com.github.tinosteinort.flda.interfaces.fixedlengthstring;

import com.github.tinosteinort.flda.interfaces.stringlist.SizeValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SizeValidatorTest {

    @Test public void valid() {
        final SizeValidator validator = new SizeValidator(5);

        final List<String> data = new ArrayList<>();
        data.add(null);
        data.add(null);
        data.add(null);
        data.add(null);
        data.add(null);

        validator.validate(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooSmall() {
        final SizeValidator validator = new SizeValidator(3);

        final List<String> data = new ArrayList<>();
        data.add(null);
        data.add(null);

        validator.validate(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBig() {
        final SizeValidator validator = new SizeValidator(3);

        final List<String> data = new ArrayList<>();
        data.add(null);
        data.add(null);
        data.add(null);
        data.add(null);

        validator.validate(data);
    }

    @Test(expected = NullPointerException.class)
    public void recordIsNull() {
        final SizeValidator validator = new SizeValidator(3);

        validator.validate(null);
    }
}
