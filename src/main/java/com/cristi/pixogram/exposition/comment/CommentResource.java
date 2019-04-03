package com.cristi.pixogram.exposition.comment;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.comment.*;
import com.cristi.pixogram.exposition.PixogramBaseRequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PixogramBaseRequestMapping
public class CommentResource {
    private final AddComment addComment;
    private final EditComment editComment;
    private final DeleteComment deleteComment;
    private final ListCommentsDescByLastModified listComments;
    private final LikeDislikeComment likeDislikeComment;
    private final ConsultComment consultComment;

    public CommentResource(
            AddComment addComment, EditComment editComment, DeleteComment deleteComment,
            ListCommentsDescByLastModified listComments, LikeDislikeComment likeDislikeComment,
            ConsultComment consultComment) {
        this.addComment = addComment;
        this.editComment = editComment;
        this.deleteComment = deleteComment;
        this.listComments = listComments;
        this.likeDislikeComment = likeDislikeComment;
        this.consultComment = consultComment;
    }


    @PostMapping("/add-comment")
    public ResponseEntity<UniqueId> addComment(@RequestBody AddCommentCommandDto command) {
        return ok(addComment.addComment(command));
    }

    @PutMapping("/edit-comment")
    public ResponseEntity<UniqueId> editComment(@RequestBody EditCommentCommandDto command) {
        return ok(editComment.editComment(command));
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<UniqueId> deleteComment(@PathVariable String commentId) {
        return ok(deleteComment.deleteComment(new UniqueId(commentId)));
    }

    @PutMapping("/like-comment")
    public ResponseEntity<UniqueId> likeComment(@RequestBody LikeDislikeCommandDto likeCommand) {
        return ok(likeDislikeComment.likeComment(likeCommand));
    }

    @PutMapping("/dislike-comment")
    public ResponseEntity<UniqueId> dislikeComment(@RequestBody LikeDislikeCommandDto dislikeCommand) {
        return ok(likeDislikeComment.dislikeComment(dislikeCommand));
    }

    @GetMapping("/{imageId}/list-comments")
    public List<CommentDetailsDto> listComments(@PathVariable String imageId) {
        return listComments.listCommentsByDateDescForImage(new UniqueId(imageId));
    }

    @GetMapping("/comments/{id}")
    public CommentDetailsDto getComment(@PathVariable String id) {
        return consultComment.consultComment(new UniqueId(id));
    }
}
