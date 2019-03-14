package com.cristi.mentool.mentool.domain.user;

import com.cristi.mentool.mentool.domain.DomainConstraintViolationException;
import org.junit.Test;

public class PhoneNumberTest {

    @Test(expected = DomainConstraintViolationException.class)
    public void should_not_allow_non_numerical_symbols() {
        new PhoneNumber("12b44");
    }


    @Test(expected = DomainConstraintViolationException.class)
    public void should_not_allow_more_than_10_digits() {
        new PhoneNumber("01234567891");
    }

    @Test
    public void should_pass_with_10_digits() {
        new PhoneNumber("0123456789");
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void should_not_be_empty() {
        new PhoneNumber("");
    }

    @Test(expected = DomainConstraintViolationException.class)
    public void should_not_be_null() {
        new PhoneNumber(null);
    }
}