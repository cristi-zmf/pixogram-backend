package com.cristi.mentool.mentool.infra.persistence.user;

import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersSdj extends JpaRepository<User, EmailAddress> {
}
