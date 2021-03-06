package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.CommentDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class ConsultCommentBack {
    private final Comments comments;

    public ConsultCommentBack(Comments comments) {
        this.comments = comments;
    }

    public CommentDetailsDto consultComment(UniqueId id) {
        return comments.getOrThrow(id).toCommentDetailsDto();
    }
}
