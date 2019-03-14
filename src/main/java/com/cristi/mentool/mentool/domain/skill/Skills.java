package com.cristi.mentool.mentool.domain.skill;

import com.cristi.mentool.mentool.domain.UniqueId;

import java.util.List;

public interface Skills {
    List<Skill> findAllWithPattern(String trainingPattern);
    Skill getOrThrow(UniqueId skillId);
    Skill add(Skill training);
    boolean exists(String skillName);
}
