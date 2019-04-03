package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.LikeDislikeCommandDto;
import org.springframework.stereotype.Service;

@Service
public class LikeDislikeComment {
    private final Comments comments;

    public LikeDislikeComment(Comments comments) {
        this.comments = comments;
    }

    public UniqueId likeComment(LikeDislikeCommandDto command) {
        Comment comment = comments.getOrThrow(new UniqueId(command.id));
        comment.like(new EmailAddress(command.author));
        return comments.add(comment).getId();
    }

    public UniqueId dislikeComment(LikeDislikeCommandDto command) {
        Comment comment = comments.getOrThrow(new UniqueId(command.id));
        comment.dislike(new EmailAddress(command.author));
        return comments.add(comment).getId();
    }
}
