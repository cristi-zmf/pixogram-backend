package com.cristi.pixogram.domain.userimage;


import com.cristi.pixogram.domain.BaseEntity;
import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 30)
    private String imageTitle;

    @NotNull
    @Length(max = 500)
    private String imageDescription;

    @NotNull
    private LocalDateTime uploadedTime;

    public UserImage(
            @NotEmpty String imagePath, @NotEmpty String imageThumbnailPath, @NotNull EmailAddress username,
            String imageTitle, String imageDescription
    ) {
        this(new UniqueId(), imagePath, imageThumbnailPath, username, imageTitle, imageDescription);
    }

    public UserImage(
            @NotNull UniqueId id, @NotEmpty String imagePath, @NotEmpty String imageThumbnailPath,
            @NotNull EmailAddress username, String imageTitle, String imageDescription
    ) {
        super(UserImage.class, id);
        this.imagePath = imagePath;
        this.imageThumbnailPath = imageThumbnailPath;
        this.username = username;
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
        this.uploadedTime = LocalDateTime.now();
        validate(this);
    }

    //USED BY JPA
    private UserImage() {
        super(UserImage.class, new UniqueId());
    }
}
