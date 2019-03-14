package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MentorsSdj extends JpaRepository<Mentor, EmailAddress> {
    @Query("select m from Mentor m  left join fetch m.trainings as t where t.skillId in :skillIds")
    Set<Mentor> findByTrainingsSkillId(@Param("skillIds") Set<UniqueId> skillIds);
}
