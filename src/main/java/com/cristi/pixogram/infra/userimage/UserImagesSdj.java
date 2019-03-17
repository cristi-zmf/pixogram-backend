package com.cristi.pixogram.infra.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserImagesSdj extends JpaRepository<UserImage, UniqueId> {
    Set<UserImage> findAllByUsername(EmailAddress username);
}
