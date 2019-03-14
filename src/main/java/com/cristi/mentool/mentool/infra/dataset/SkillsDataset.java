package com.cristi.mentool.mentool.infra.dataset;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.skill.Skill;

import java.util.List;

import static java.util.Arrays.asList;

public class SkillsDataset {
    public final static Skill JAVA = new Skill(new UniqueId(), "Java");
    public final static Skill MICROSERVICES = new Skill(new UniqueId(), "Microservices");
    public final static Skill PYTHON = new Skill(new UniqueId(), "Python");
    public final static Skill PHP = new Skill(new UniqueId(), "Php");
    public final static Skill KOTLIN = new Skill(new UniqueId(), "Kotlin");

    public static List<Skill> getAllSkills() {
        return asList(JAVA, MICROSERVICES, PYTHON, PHP, KOTLIN);
    }
}
