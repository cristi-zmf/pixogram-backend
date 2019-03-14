package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.MentorTraining;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorTrainingsSdj extends JpaRepository<MentorTraining, UniqueId> {
}
