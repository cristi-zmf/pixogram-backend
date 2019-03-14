package com.cristi.mentool.mentool.domain.user;

public class ValidUserGenerator {

    public static final User CRISTI = new User(
            new EmailAddress("cristi@cristi.com"), "Cristi", "Cucumber warchief",
            new PhoneNumber("1234567890")
    );
}
