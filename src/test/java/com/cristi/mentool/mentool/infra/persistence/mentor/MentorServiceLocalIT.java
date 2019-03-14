package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.exposition.mentor.MentorEditCommand;
import com.cristi.mentool.mentool.domain.mentor.MentorService;
import com.cristi.mentool.mentool.domain.mentor.MentorTraining;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.PhoneNumber;
import com.cristi.mentool.mentool.infra.dataset.MentorDataSet;
import com.cristi.mentool.mentool.infra.dataset.SkillsDataset;
import com.cristi.mentool.mentool.infra.persistence.IntegrationTestWithDataset;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class MentorServiceLocalIT extends IntegrationTestWithDataset {

    @Autowired
    private MentorsSdj sdj;
    @Autowired
    private MentorService sut;

    @Test
    public void registerMentor() {
        MentorEditCommand command = someRegistrationCommand();
        Mentor expected = getExpectedFromRegistrationCommand(command);
        Mentor actual = sut.registerMentor(command);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void registerTraining() {
        EmailAddress linusId = MentorDataSet.LINUS.getId();
        MentorTraining training = someNewTraining(linusId);
        Mentor linus = sdj.findById(linusId).orElseThrow(NoSuchElementException::new);
        int numberOfTrainingsBeforeNewTraining = linus.getTrainings().size();
        sut.registerTraining(training);
        linus = sdj.findById(linusId).orElseThrow(NoSuchElementException::new);
        int numberOfTrainingsAfterAddingNewTraining = linus.getTrainings().size();
        MentorTraining savedTraining = linus.getTraining(training.getId()).orElseThrow(NoSuchElementException::new);
        assertThat(numberOfTrainingsAfterAddingNewTraining).isEqualTo(numberOfTrainingsBeforeNewTraining + 1);
        assertThat(savedTraining).isEqualToComparingFieldByFieldRecursively(training);
    }

    @Test
    public void removeTraining() {
        Mentor linus = sdj.findById(MentorDataSet.LINUS.getId()).orElseThrow(NoSuchElementException::new);
        MentorTraining trainingToRemove = linus.getTrainings().stream().findFirst().orElseThrow(NoSuchElementException::new);
        int numberOfTrainingsBeforeRemoval = linus.getTrainings().size();
        sut.removeTrainings(linus.getId(), singleton(trainingToRemove.getId()));
        linus = sdj.findById(linus.getId()).orElseThrow(NoSuchElementException::new);
        Set<MentorTraining> actualTrainings = linus.getTrainings();
        assertThat(actualTrainings).hasSize(numberOfTrainingsBeforeRemoval - 1);
        assertThat(actualTrainings).doesNotContain(trainingToRemove);
    }

    private MentorEditCommand someRegistrationCommand() {
        return new MentorEditCommand(
                "Chupa", "Chups", "chupa@chups.com",
                "1234567890", 10, "www.linkedin.com"
        );
    }

    private Mentor getExpectedFromRegistrationCommand(MentorEditCommand command) {
        return new Mentor(
                new EmailAddress(command.getUsername()), command.getFirstName(), command.getLastName(),
                new PhoneNumber(command.getPhoneNumber()), command.getYearsOfExperience(), command.getLinkedinUrl(),
                new HashSet<>(), 0
        );
    }

    private MentorTraining someNewTraining(EmailAddress linusId) {
        return new MentorTraining(
                new UniqueId(), "super new training",
                SkillsDataset.KOTLIN.getId(), "java", 2,
                linusId, new BigDecimal(300)
        );
    }
}
