package com.cristi.mentool.mentool.exposition.skill;

import com.cristi.mentool.mentool.domain.skill.Skill;
import com.cristi.mentool.mentool.exposition.BaseExpoTest;
import com.cristi.mentool.mentool.infra.persistence.skill.SkillsSdj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("NO_DATA_SET")
public class SkillResourceLocalIT extends BaseExpoTest {
    @Autowired
    private SkillsSdj skillsSdj;

    @Test
    public void addSkill_should_add_skill() {
        assertThat(skillsSdj.findAll()).isEmpty();
        String url = format("http://localhost:%d/persons/skills", port);
        SkillCreateCommand command = new SkillCreateCommand("java");
        this.restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(command), Skill.class);
        assertThat(skillsSdj.findAll()).hasSize(1);
        assertThat(skillsSdj.findAll().get(0)).isEqualToIgnoringGivenFields(new Skill("java"), "id");
    }
}