package com.cristi.mentool.mentool.infra.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.userimage.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserImagesSdj extends JpaRepository<UserImage, UniqueId> {
    Set<UserImage> findAllByUsername(EmailAddress username);
}
