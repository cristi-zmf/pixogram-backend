package com.cristi.mentool.mentool.exposition.user;

import com.cristi.mentool.mentool.domain.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@NoArgsConstructor
public class UserRegistrationCommand  {
    @JsonProperty
    public User newUser;
    @JsonProperty
    public String password;

    public UserRegistrationCommand(User newUser, String password) {
        this.newUser = newUser;
        this.password = password;
    }
}
