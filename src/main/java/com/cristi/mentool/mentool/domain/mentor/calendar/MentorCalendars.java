package com.cristi.mentool.mentool.domain.mentor.calendar;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface MentorCalendars {
    List<UniqueId> findAllTrainingIdsInInterval(LocalDateTime startTime, LocalDateTime endTime);

    MentorCalendar add(MentorCalendar calendarEntry);

    MentorCalendar findByTraining(UniqueId trainingId);

    Set<MentorCalendar> findByTraineeAddress(EmailAddress traineeAddress);
    Set<MentorCalendar> findByTrainingIds(Set<UniqueId> trainingIds);
}
