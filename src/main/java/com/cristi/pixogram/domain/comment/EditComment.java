package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.EditCommentCommandDto;
import org.springframework.stereotype.Service;

@Service
public class EditComment {
    private final Comments comments;

    public EditComment(Comments comments) {
        this.comments = comments;
    }

    public UniqueId editComment(EditCommentCommandDto command) {
        Comment comment = comments.getOrThrow(new UniqueId(command.id));
        if (!comment.isAuthor(new EmailAddress(command.author))) {
            throw new IllegalArgumentException("Only the author can modify the comment");
        }
        comment.editValue(command.value);
        return comments.add(comment).getId();
    }
}
