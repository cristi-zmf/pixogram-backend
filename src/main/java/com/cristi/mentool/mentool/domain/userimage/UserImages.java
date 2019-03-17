package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;

import java.util.Set;

public interface UserImages {
    Set<UserImage> findAllByUsername(EmailAddress username);
    UserImage getOrThrow(UniqueId imageId);
    UserImage add(UserImage userImage);
}
