package com.cristi.mentool.mentool.domain.user;

import com.cristi.mentool.mentool.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@NoArgsConstructor
@Getter
public class BaseUser extends BaseEntity<BaseUser, EmailAddress> {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private PhoneNumber phoneNumber;

    protected BaseUser(EmailAddress username) {
        super(BaseUser.class, username);
    }


    public BaseUser(
            @NotNull EmailAddress id, @NotBlank String firstName,
            @NotBlank String lastName, @NotNull PhoneNumber phoneNumber
    ) {
        super(BaseUser.class, id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
