package com.cristi.pixogram.domain.comment;

import com.cristi.pixogram.domain.BaseEntity;
import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.comment.CommentDetailsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Getter
public class Comment extends BaseEntity<Comment, UniqueId> {
    @NotEmpty
    private String value;
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "IMAGE_ID"))
    private UniqueId imageId;
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "AUTHOR"))
    private EmailAddress author;
    @NotNull
    @ElementCollection
    private Set<EmailAddress> likes;
    @NotNull
    @ElementCollection
    private Set<EmailAddress> disLikes;
    @NotNull
    private LocalDateTime lastModified;

    private Comment(
            UniqueId id, @NotNull String value, @NotNull UniqueId imageId,
            @NotNull EmailAddress author, @NotNull Set<EmailAddress> likes, @NotNull Set<EmailAddress> disLikes,
            LocalDateTime lastModified) {
        super(Comment.class, id);
        this.value = value;
        this.imageId = imageId;
        this.author = author;
        this.likes = new HashSet<>(likes);
        this.disLikes = new HashSet<>(disLikes);
        this.lastModified = lastModified;
        validate(this);
    }

    public Comment(
            @NotNull String value, @NotNull UniqueId imageId, @NotNull EmailAddress author,
            @NotNull Set<EmailAddress> likes, @NotNull Set<EmailAddress> disLikes, @NotNull LocalDateTime lastModified
    ) {
        this(new UniqueId(), value, imageId, author, likes, disLikes, lastModified);
    }

    public Set<EmailAddress> getLikes() {
        return new HashSet<>(likes);
    }

    public Set<EmailAddress> getDisLikes() {
        return new HashSet<>(disLikes);
    }

    public Set<EmailAddress> like(EmailAddress address) {
        likes.add(address);
        disLikes.remove(address);
        return likes;
    }

    public Set<EmailAddress> dislike(EmailAddress address) {
        disLikes.add(address);
        likes.remove(address);
        return disLikes;
    }

    public String editValue(String newValue) {
        value = newValue;
        lastModified = LocalDateTime.now();
        validate(this);
        return value;
    }

    public boolean isAuthor(EmailAddress person) {
        return author.equals(person);
    }

    public CommentDetailsDto toCommentDetailsDto() {
        return new CommentDetailsDto(
                getId().getValue(), value, author.getValue(), imageId.getValue(), toRawEmailAddresses(getLikes()),
                toRawEmailAddresses(getDisLikes()), lastModified
        );
    }

    private Set<String> toRawEmailAddresses(@NotNull Set<EmailAddress> addresses) {
        return addresses.stream().map(EmailAddress::getValue).collect(toSet());
    }

    //USED BY JPA
    private Comment() {
        super(Comment.class, new UniqueId());
    }
}
