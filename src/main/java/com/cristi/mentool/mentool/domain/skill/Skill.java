package com.cristi.mentool.mentool.domain.skill;

import com.cristi.mentool.mentool.domain.BaseEntity;
import com.cristi.mentool.mentool.domain.UniqueId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;


@Entity
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Skill extends BaseEntity<Skill, UniqueId> {
    @NotEmpty
    @Column(name = "SKILL_NAME")
    private String skillName;

    public Skill(UniqueId id, @NotEmpty String skillName) {
        super(Skill.class, id);
        this.skillName = skillName;
    }

    public Skill(String skillName) {
        this(new UniqueId(), skillName);
    }

    public String getSkillName() {
        return skillName;
    }
}
