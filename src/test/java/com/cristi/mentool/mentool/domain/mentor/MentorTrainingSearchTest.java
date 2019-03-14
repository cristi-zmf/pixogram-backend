package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.calendar.InMemoryMentorCalendars;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendar;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorCalendars;
import com.cristi.mentool.mentool.domain.skill.InMemorySkills;
import com.cristi.mentool.mentool.domain.skill.Skill;
import com.cristi.mentool.mentool.domain.skill.Skills;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static com.cristi.mentool.mentool.infra.dataset.MentorDataSet.LINUS;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class MentorTrainingSearchTest {
    private static final LocalDateTime START_TIME = ValidRomanianIntervalGenerator.START_TIME;
    private static final LocalDateTime END_TIME = ValidRomanianIntervalGenerator.END_TIME;
    private Skills inMemorySkills = new InMemorySkills();
    private MentorCalendars inMemoryCalendars = new InMemoryMentorCalendars();
    private Mentors inMemoryMentors = new InMemoryMentors();
    private MentorTrainingSearch sut = new MentorTrainingSearch(inMemorySkills, inMemoryMentors, inMemoryCalendars);
    private MentorInput mentorInput;

    @Before
    public void setupTestDataset() {
        mentorInput = new MentorInput().setupMentor();
        setupSkill(mentorInput.mentorTraining);
        setupCalendar(mentorInput.mentorTraining);
    }


    @Test
    public void should_return_results_matching_the_training_pattern_and_date_times() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "ava", START_TIME.minusHours(1L), END_TIME.plusDays(1L)
        );
        checkExpectationsForActualResults(results);
    }

    @Test
    public void should_return_results_matching_the_training_pattern_and_date_times_is_start_date_is_equal_to_skill_start() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "ava", START_TIME, END_TIME.plusDays(1L)
        );
        checkExpectationsForActualResults(results);
    }

    @Test
    public void should_return_results_matching_the_training_pattern_and_date_times_is_end_date_is_equal_to_skill_end() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "ava", START_TIME.minusHours(1L), END_TIME
        );
        checkExpectationsForActualResults(results);
    }


    @Test
    public void should_return_empty_results_if_end_time_of_training_is_after_input_end_time() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "AVA", START_TIME.minusHours(1L), END_TIME.minusHours(1L)
        );
        assertThat(results).isEmpty();
    }

    @Test
    public void should_return_empty_results_if_start_time_of_training_is_before_input_start_time() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "AVA", START_TIME.plusMinutes(1L), END_TIME.plusHours(1L)
        );
        assertThat(results).isEmpty();
    }

    @Test
    public void should_return_empty_results_if_skill_pattern_does_not_match() {
        List<MentorSearchResult> results = sut.searchForMentors(
                "AVAc", START_TIME.minusHours(1L), END_TIME.plusHours(1L)
        );
        assertThat(results).isEmpty();
    }


    private void checkExpectationsForActualResults(List<MentorSearchResult> results) {
        MentorSearchResult expectedResult = expectedResult(mentorInput);
        assertThat(results).isNotEmpty();
        assertThat(results).usingFieldByFieldElementComparator().containsExactly(expectedResult);
    }

    private MentorSearchResult expectedResult(MentorInput mentorInput) {
        Mentor mentor = mentorInput.getMentor();
        MentorTraining training = mentorInput.getMentorTraining();
        return new MentorSearchResult(
                mentor.getFirstName(), mentor.getLastName(), mentor.getYearsOfExperience(), mentor.getNoOfOverallTrainingsDone(),
                training.getNoOfTrainingsDone(), new BigDecimal(100), "JAVA",
                training.getId().getValue());
    }

    private class MentorInput {
        private Mentor linus;
        private MentorTraining mentorTraining;

        Mentor getMentor() {
            return linus;
        }

        MentorTraining getMentorTraining() {
            return mentorTraining;
        }

        MentorInput setupMentor() {
            linus = LINUS;
            inMemoryMentors.add(linus);
            mentorTraining = linus.getTrainings().stream()
                    .findFirst().orElseThrow(NoSuchElementException::new);
            return this;
        }
    }

    private void setupSkill(MentorTraining mentorTraining) {
        UniqueId skillId = mentorTraining.getSkillId();
        Skill skill = new Skill(skillId, "JAVA");
        inMemorySkills.add(skill);
    }

    private void setupCalendar(MentorTraining mentorTraining) {
        MentorCalendar calendarEntry = new MentorCalendar(
                new UniqueId(), mentorTraining.getId(), START_TIME, END_TIME,
                20, emptySet()
        );
        inMemoryCalendars.add(calendarEntry);
    }
}
