package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;

import java.util.List;
import java.util.Set;

public interface Mentors {
    Mentor add(Mentor mentor);
    Mentor getOrThrow(EmailAddress username);
    Set<Mentor> findAll();

    List<Mentor> findAllMentorsTeachingTheSkills(Set<UniqueId> skillIds);

    List<Mentor> findAll(List<EmailAddress> matchingMentorIds);
    boolean exists(EmailAddress address);
}
