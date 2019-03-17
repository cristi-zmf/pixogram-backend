package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;

import java.util.Set;

public interface UserImages {
    Set<UserImage> findAllByUsername(EmailAddress username);
    UserImage getOrThrow(UniqueId imageId);
    UserImage add(UserImage userImage);
}
