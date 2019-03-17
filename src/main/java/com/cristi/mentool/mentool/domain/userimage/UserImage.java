package com.cristi.mentool.mentool.domain.userimage;


import com.cristi.mentool.mentool.domain.BaseEntity;
import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;
import lombok.Getter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Entity
public class UserImage extends BaseEntity<UserImage, UniqueId> {
    @NotEmpty
    private String imagePath;
    @NotEmpty
    private String imageThumbnailPath;
    @NotNull
    private EmailAddress username;

    @NotNull
    private LocalDateTime uploadedTime;

    public UserImage(@NotEmpty String imagePath, @NotEmpty String imageThumbnailPath, @NotNull EmailAddress username) {
        this(new UniqueId(), imagePath, imageThumbnailPath, username);
    }

    public UserImage(@NotNull UniqueId id, @NotEmpty String imagePath, @NotEmpty String imageThumbnailPath, @NotNull EmailAddress username) {
        super(UserImage.class, id);
        this.imagePath = imagePath;
        this.imageThumbnailPath = imageThumbnailPath;
        this.username = username;
        this.uploadedTime = LocalDateTime.now();
        validate(this);
    }

    //USED BY JPA
    private UserImage() {
        super(UserImage.class, new UniqueId());
    }
}
