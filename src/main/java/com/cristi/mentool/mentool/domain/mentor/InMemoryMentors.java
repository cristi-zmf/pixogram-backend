package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class InMemoryMentors implements Mentors {
    private final Set<Mentor> db = new HashSet<>();

    @Override
    public Mentor add(Mentor mentor) {
        db.add(mentor);
        return mentor;
    }

    @Override
    public Mentor getOrThrow(EmailAddress username) {
        return db.stream().filter(m -> m.getId().equals(username))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Mentor> findAll() {
        return new HashSet<>(db);
    }

    @Override
    public List<Mentor> findAllMentorsTeachingTheSkills(Set<UniqueId> skillIds) {
        return db.stream().flatMap(m -> m.getTrainings().stream())
                .filter(t -> skillIds.contains(t.getSkillId()))
                .map(t -> getOrThrow(t.getMentorId()))
                .collect(toList());
    }

    @Override
    public List<Mentor> findAll(List<EmailAddress> matchingMentorIds) {
        return db.stream()
                .filter(m -> matchingMentorIds.contains(m.getId()))
                .collect(toList())
                ;
    }

    @Override
    public boolean exists(EmailAddress address) {
        return db.stream().anyMatch(m -> m.getId().equals(address));
    }
}
