package com.cristi.mentool.mentool.domain.user;

import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class RegisterUser {
    private final Users users;

    public RegisterUser(Users users) {
        this.users = users;
    }
    public User registerUser(User newUser) {
        EmailAddress address = newUser.getId();
        if (users.exists(address)) {
            throw new IllegalStateException(format("User already exists with address <%s>", address));
        }
        return users.add(newUser);
    }
}
