package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;

import java.util.Set;

public interface MentorTrainings {
    Set<MentorTraining> findByTrainingIds(Set<UniqueId> trainingIds);
    MentorTraining getOrThrow(UniqueId trainingId);
}
