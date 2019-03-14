package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendar;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface MentorCalendarsSdj extends JpaRepository<MentorCalendar, UniqueId> {
    @Query("select c from MentorCalendar c where :startTime <= c.startTime  and :endTime >= c.endTime ")
    Set<MentorCalendar> findByStartTimeAndEndTime(
            @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime
    );

    Optional<MentorCalendar> findByTrainingId(UniqueId trainingId);
    Set<MentorCalendar> findByTraineesBookedContains(EmailAddress traineeEmail);
    @Query("select c from MentorCalendar  c where c.trainingId in :trainingIds")
    Set<MentorCalendar> findByTrainingId(@Param("trainingIds") Set<UniqueId> trainingIds);

}
