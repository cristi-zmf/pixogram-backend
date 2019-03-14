package com.cristi.mentool.mentool.domain.user;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class User extends BaseUser {
    public User(
            @NotNull EmailAddress username, @NotBlank String firstName, @NotBlank String lastName,
            @NotNull PhoneNumber phoneNumber
    ) {
        super(username, firstName, lastName, phoneNumber);
        validate(this);
    }
}
