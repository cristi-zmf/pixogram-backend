package com.cristi.mentool.mentool.domain.user;

import java.util.Set;

public interface Users {
    User add(User user);
    Set<User> findAll();
    Set<User> findAll(Set<EmailAddress> emails);
    User getOrThrow(EmailAddress userId);
    boolean exists(EmailAddress address);
}
