package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendar;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.ValidUserGenerator;
import com.cristi.mentool.mentool.infra.dataset.MentorDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SdjMentorCalendars_WithPreloadedDatasetLocalIT {
    @Autowired
    private SdjMentorCalendars sut;

    @Test
    public void findByTraineeAddress_should_return_all_trainings_booked_by_that_email() {
        EmailAddress traineeThatBookedEmail = ValidUserGenerator.CRISTI.getId();
        Set<MentorCalendar> actualResults = sut.findByTraineeAddress(traineeThatBookedEmail);
        int expectedNumberOfResults = MentorDataSet.HERCULES.getTrainings().size();
        assertThat(actualResults).hasSize(expectedNumberOfResults);
        assertThat(actualResults).allMatch(
                c -> c.getTraineesBooked().contains(traineeThatBookedEmail)
        );
    }
}
