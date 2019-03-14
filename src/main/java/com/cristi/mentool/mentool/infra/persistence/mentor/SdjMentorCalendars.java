package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendar;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendars;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Repository
public class SdjMentorCalendars implements MentorCalendars {
    private final MentorCalendarsSdj sdj;

    public SdjMentorCalendars(MentorCalendarsSdj sdj) {
        this.sdj = sdj;
    }

    @Override
    public List<UniqueId> findAllTrainingIdsInInterval(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            startTime = LocalDateTime.MAX;
        }
        if (endTime == null) {
            endTime = LocalDateTime.MIN;
        }
        return sdj.findByStartTimeAndEndTime(startTime, endTime).stream()
                .map(MentorCalendar::getTrainingId)
                .collect(toList());
    }

    @Override
    public MentorCalendar add(MentorCalendar calendarEntry) {
        return sdj.saveAndFlush(calendarEntry);
    }

    @Override
    public MentorCalendar findByTraining(UniqueId trainingId) {
        return sdj.findByTrainingId(trainingId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<MentorCalendar> findByTraineeAddress(EmailAddress traineeAddress) {
        return sdj.findByTraineesBookedContains(traineeAddress);
    }

    @Override
    public Set<MentorCalendar> findByTrainingIds(Set<UniqueId> trainingIds) {
        return sdj.findByTrainingId(trainingIds);
    }
}
