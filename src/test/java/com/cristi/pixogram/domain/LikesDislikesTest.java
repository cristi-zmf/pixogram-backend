package com.cristi.pixogram.domain;

import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class LikesDislikesTest {
    private LikesDislikes sut;
    private EmailAddress john;
    private EmailAddress mark;

    @Before
    public void setupSut() {
        john = new EmailAddress("john@john.com");
        mark = new EmailAddress("mark@mark.com");
        sut = new LikesDislikes(singleton(john), singleton(mark));
    }

    @Test
    public void like() {
        sut.like(mark);
        assertThat(sut.getLikes()).containsExactlyInAnyOrder(john, mark);
        assertThat(sut.getDislikes()).isEmpty();
    }
    @Test
    public void dislike() {
        sut.dislike(john);
        assertThat(sut.getLikes()).isEmpty();
        assertThat(sut.getDislikes()).containsExactlyInAnyOrder(mark, john);
    }

    @Test
    public void dislike_with_already_disliked_email() {
        sut.dislike(mark);
        assertThat(sut.getLikes()).containsExactly(john);
        assertThat(sut.getDislikes()).containsExactly(mark);
    }
}