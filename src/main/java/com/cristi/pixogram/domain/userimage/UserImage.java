package com.cristi.pixogram.domain.userimage;


import com.cristi.pixogram.domain.BaseEntity;
import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.LikesDislikes;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.update.ImageIdentificationInfoUpdateCommand;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

import static java.util.Collections.emptySet;

//TODO add last modified date (maybe for newsfeed)
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

    @NotNull
    private LikesDislikes likesDislikes;

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
        this.likesDislikes = new LikesDislikes(emptySet(), emptySet());
        validate(this);
    }

    //USED BY JPA
    private UserImage() {
        super(UserImage.class, new UniqueId());
    }

    public UserImage updateIdentificationInfo(ImageIdentificationInfoUpdateCommand updateCommand) {
        if (!username.equals(updateCommand.getOwner())) {
            throw new IllegalArgumentException("Can not modify picture identification info. Not the owner of the picture.");
        }
        imageDescription = updateCommand.getDescription();
        imageTitle = updateCommand.getTitle();
        return this;
    }

    public Set<EmailAddress> getLikes() {
        return likesDislikes.getLikes();
    }

    public Set<EmailAddress> getDislikes() {
        return likesDislikes.getDislikes();
    }

    public void like(EmailAddress address) {
        likesDislikes.like(address);
    }

    public void dislike(EmailAddress address) {
        likesDislikes.dislike(address);
    }
}
