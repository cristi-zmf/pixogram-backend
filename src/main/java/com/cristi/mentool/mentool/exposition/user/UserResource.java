package com.cristi.mentool.mentool.exposition.user;

import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.RegisterUser;
import com.cristi.mentool.mentool.domain.user.User;
import com.cristi.mentool.mentool.domain.user.Users;
import com.cristi.mentool.mentool.exposition.MentoolRequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@MentoolRequestMapping
public class UserResource {

    private final RegisterUser registerUser;
    private final Users users;

    public UserResource(RegisterUser registerUser, Users users) {
        this.registerUser = registerUser;
        this.users = users;
    }
    @PostMapping(value = "/users")
    public EmailAddress registerUser(@Valid @RequestBody UserDto newUser) {
        return registerUser.registerUser(newUser.toUser()).getId();
    }

    @PutMapping(value = "/users")
    public EmailAddress updateUser(@Valid @RequestBody UserDto newUser) {
        return users.add(newUser.toUser()).getId();
    }

    @GetMapping(value = "/users/{emailAddress}")
    public User getUser(@PathVariable String emailAddress) {
        return users.getOrThrow(new EmailAddress(emailAddress));
    }
}
