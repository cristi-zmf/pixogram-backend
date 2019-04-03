package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.UniqueId;

import java.util.List;

public interface Comments {
    List<Comment> findByImageIdDesc(UniqueId imageId);
    Comment add(Comment comment);
    Comment getOrThrow(UniqueId id);
    void remove(UniqueId id);
}
