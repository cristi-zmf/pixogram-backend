package com.cristi.mentool.mentool.infra.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.userimage.UserImage;
import com.cristi.mentool.mentool.domain.userimage.UserImages;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Set;

@Repository
public class SdjUserImages implements UserImages {
    private final UserImagesSdj sdj;

    public SdjUserImages(UserImagesSdj sdj) {
        this.sdj = sdj;
    }

    @Override
    public Set<UserImage> findAllByUsername(EmailAddress username) {
        return sdj.findAllByUsername(username);
    }

    @Override
    public UserImage getOrThrow(UniqueId imageId) {
        return sdj.findById(imageId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserImage add(UserImage userImage) {
        return sdj.saveAndFlush(userImage);
    }
}
