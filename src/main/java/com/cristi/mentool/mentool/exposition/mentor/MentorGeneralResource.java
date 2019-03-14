package com.cristi.mentool.mentool.exposition.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.domain.mentor.MentorService;
import com.cristi.mentool.mentool.domain.mentor.MentorTraining;
import com.cristi.mentool.mentool.domain.mentor.Mentors;
import com.cristi.mentool.mentool.domain.mentor.calendar.MentorTrainingDetails;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.exposition.MentoolRequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@MentoolRequestMapping
public class MentorGeneralResource {
    private final MentorService mentorService;
    private final Mentors mentors;

    public MentorGeneralResource(MentorService mentorService, Mentors mentors) {
        this.mentorService = mentorService;
        this.mentors = mentors;
    }

    @GetMapping("/mentors/{address}")
    public Mentor getMentorDetails(@PathVariable("address") EmailAddress address) {
        return mentors.getOrThrow(address);
    }

    @PutMapping("/mentors")
    public ResponseEntity<EmailAddress> registerMentor(@Valid @RequestBody MentorEditCommand registrationCommand) {
        return ok(mentorService.registerMentor(registrationCommand).getId());
    }

    @PostMapping("/mentors")
    public ResponseEntity<EmailAddress> updatedMentor(@Valid @RequestBody MentorEditCommand editCommand) {
        return ok(mentorService.updateMentor(editCommand).getId());
    }

    @GetMapping("/mentors/{mentorAddress}/trainings")
    public Set<MentorTrainingDetails> viewTrainings(@PathVariable @Valid EmailAddress mentorAddress) {
        return mentorService.viewTrainingsDetails(mentorAddress);
    }

    @GetMapping("/mentors/trainings/{trainingId}")
    public MentorTrainingDetails viewTrainingDetails(@PathVariable @Valid UniqueId trainingId) {
        return mentorService.viewTrainingDetails(trainingId);
    }

    @PostMapping("/mentors/trainings")
    public ResponseEntity<EmailAddress> addTraining(@Valid @RequestBody MentorTraining newTraining) {
        return ok(mentorService.registerTraining(newTraining).getId());
    }

    @DeleteMapping("/mentors/trainings")
    public ResponseEntity<EmailAddress> removeTrainings(@Valid @RequestBody TrainingsRemovalCommand removalCommand) {
        Mentor mentorWithRemovedTrainings = mentorService.removeTrainings(
                removalCommand.mentorAddress, removalCommand.trainingIdsToBeRemoved
        );
        return ok(mentorWithRemovedTrainings.getId());
    }
}
