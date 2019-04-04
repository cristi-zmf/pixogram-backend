package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.LikeDislikeCommandDto;
import org.springframework.stereotype.Service;

@Service
public class LikeDislikeImage {
    private final UserImages userImages;

    public LikeDislikeImage(UserImages userImages) {
        this.userImages = userImages;
    }

    public UniqueId likeImage(LikeDislikeCommandDto command) {
        UserImage image = userImages.getOrThrow(command.domainId());
        image.like(command.domainAuthor());
        return userImages.add(image).getId();
    }

    public UniqueId dislikeImage(LikeDislikeCommandDto command) {
        UserImage image = userImages.getOrThrow(command.domainId());
        image.dislike(command.domainAuthor());
        return userImages.add(image).getId();
    }
}
