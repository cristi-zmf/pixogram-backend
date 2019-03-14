package com.cristi.mentool.mentool.infra.persistence.user;

import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.PhoneNumber;
import com.cristi.mentool.mentool.domain.user.User;

public class ValidUserGenerator {
    public static User gigiUser() {
        return new User(
                new EmailAddress("cristi.gigi@test.com"), "Cristi", "Gigi",
                new PhoneNumber("0711456867")
        );
    }
}