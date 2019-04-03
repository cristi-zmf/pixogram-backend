package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.UniqueId;
import org.springframework.stereotype.Service;

@Service
public class DeleteComment {
    private final Comments comments;

    public DeleteComment(Comments comments) {
        this.comments = comments;
    }

    public UniqueId deleteComment(UniqueId id) {
        comments.remove(id);
        return id;
    }
}
