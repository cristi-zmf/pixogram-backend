package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.CommentDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ListCommentsDescByLastModified {
    private final Comments comments;

    public ListCommentsDescByLastModified(Comments comments) {
        this.comments = comments;
    }

   public List<CommentDetailsDto> listCommentsByDateDescForImage(UniqueId imageId) {
        return comments.findByImageIdDesc(imageId).stream()
                .map(Comment::toCommentDetailsDto)
                .collect(toList());
   }
}
