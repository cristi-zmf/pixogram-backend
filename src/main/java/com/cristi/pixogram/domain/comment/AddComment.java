package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.AddCommentCommandDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Collections.emptySet;

@Service
public class AddComment {
    private final Comments comments;

    public AddComment(Comments comments) {
        this.comments = comments;
    }

    public UniqueId addComment(AddCommentCommandDto command) {
        Comment newComment = new Comment(
                command.value, new UniqueId(command.imageId),
                new EmailAddress(command.author), emptySet(), emptySet(), LocalDateTime.now()
        );
        return comments.add(newComment).getId();
    }
}
