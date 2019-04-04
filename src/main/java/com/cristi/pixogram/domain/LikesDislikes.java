package com.cristi.pixogram.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

@Embeddable
public class LikesDislikes extends BaseValueObject<LikesDislikes> {
    @ElementCollection
    @NotNull
    private final Set<EmailAddress> likes;
    @ElementCollection
    @NotNull
    private final Set<EmailAddress> dislikes;

    public LikesDislikes(Set<EmailAddress> likes, Set<EmailAddress> dislikes) {
        super(LikesDislikes.class);
        this.likes = new HashSet<>(likes);
        this.dislikes = new HashSet<>(dislikes);
        validate(this);
    }

    public void like(EmailAddress address) {
        dislikes.remove(address);
        likes.add(address);
    }

    public void dislike(EmailAddress address) {
        likes.remove(address);
        dislikes.add(address);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(likes, dislikes);
    }

    public Set<EmailAddress> getLikes() {
        return new HashSet<>(likes);
    }

    public Set<EmailAddress> getDislikes() {
        return new HashSet<>(dislikes);
    }

    /*USED BY JPA*/
    private LikesDislikes() {
        super(LikesDislikes.class);
        likes = new HashSet<>();
        dislikes = new HashSet<>();
    }
}
