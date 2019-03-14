package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SdjMentorCalendarsLocalIT {
    @Autowired
    private MentorCalendarsSdj sdj;
    @Autowired
    private SdjMentorCalendars sut;
    private MentorCalendar entry2;
    private MentorCalendar entry1;

    @Before
    public void setup() {
        sdj.deleteAll();
        entry1 = new MentorCalendar(
                new UniqueId(), new UniqueId(), LocalDateTime.parse("2018-12-23T23:00:00"), LocalDateTime.parse("2018-12-27T23:00:00"),
                20, emptySet()
        );
        entry2 = new MentorCalendar(
                new UniqueId(), new UniqueId(), LocalDateTime.parse("2018-11-20T23:00:00"), LocalDateTime.parse("2018-12-24T23:00:00"),
                15, emptySet()
        );
        sdj.saveAll(asList(entry1, entry2));
    }

    @Test
    public void findAllInInterval_should_return_all_when_null_dates() {
        List<UniqueId> actualResults = sut.findAllTrainingIdsInInterval(null, null);
        assertThat(actualResults).isNotEmpty();
        assertThat(actualResults).hasSameElementsAs(getExpectedResults());
    }

    private List<UniqueId> getExpectedResults() {
        return asList(entry1, entry2).stream().map(MentorCalendar::getTrainingId).collect(toList());
    }
}
