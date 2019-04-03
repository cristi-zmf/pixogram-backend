package com.cristi.pixogram.infra.comment;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsSdj extends JpaRepository<Comment, UniqueId> {
    List<Comment> findByImageIdOrderByLastModifiedDesc(UniqueId imageId);
}
