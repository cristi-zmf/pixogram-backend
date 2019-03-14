package com.cristi.mentool.mentool.domain.skill;

import com.cristi.mentool.mentool.domain.UniqueId;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class InMemorySkills implements Skills {
    private final Set<Skill> db;

    public InMemorySkills() {
        this.db = new HashSet<>();
    }

    @Override
    public List<Skill> findAllWithPattern(String trainingPattern) {
        String lowerCasePattern = trainingPattern.toLowerCase();
        return db.stream().filter(t -> t.getSkillName().toLowerCase().contains(lowerCasePattern))
                .collect(toList());

    }

    @Override
    public Skill getOrThrow(UniqueId skillId) {
        return db.stream().filter(s -> skillId.equals(s.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Skill add(Skill training) {
        db.add(training);
        return training;
    }

    @Override
    public boolean exists(String skillName) {
        return db.stream().anyMatch(s -> s.getSkillName().equalsIgnoreCase(skillName));
    }
}
