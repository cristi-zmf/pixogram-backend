package com.cristi.pixogram.infra.comment;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.comment.Comment;
import com.cristi.pixogram.domain.comment.Comments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class SdjComments implements Comments {
    private final CommentsSdj sdj;

    public SdjComments(CommentsSdj sdj) {
        this.sdj = sdj;
    }

    @Override
    public List<Comment> findByImageIdDesc(UniqueId imageId) {
        return sdj.findByImageIdOrderByLastModifiedDesc(imageId);
    }

    @Override
    public Comment add(Comment comment) {
        return sdj.saveAndFlush(comment);
    }

    @Override
    public Comment getOrThrow(UniqueId id) {
        return sdj.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void remove(UniqueId id) {
        sdj.deleteById(id);
    }
}
