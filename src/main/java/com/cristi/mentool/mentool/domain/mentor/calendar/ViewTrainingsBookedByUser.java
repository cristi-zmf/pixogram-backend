package com.cristi.mentool.mentool.domain.mentor.calendar;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.domain.mentor.MentorTraining;
import com.cristi.mentool.mentool.domain.mentor.MentorTrainings;
import com.cristi.mentool.mentool.domain.mentor.Mentors;
import com.cristi.mentool.mentool.domain.skill.Skill;
import com.cristi.mentool.mentool.domain.skill.Skills;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class ViewTrainingsBookedByUser {
    private final MentorCalendars calendar;
    private final Skills skills;
    private final MentorTrainings mentorTrainings;
    private final Mentors mentors;

    public ViewTrainingsBookedByUser(MentorCalendars calendar, Skills skills, MentorTrainings mentorTrainings, Mentors mentors) {
        this.calendar = calendar;
        this.skills = skills;
        this.mentorTrainings = mentorTrainings;
        this.mentors = mentors;
    }

    public Set<MentorTrainingDetails> viewTrainingsBookedByUser(EmailAddress userAddress) {
        Set<MentorCalendar> bookedTrainingsByUser = calendar.findByTraineeAddress(userAddress);
        Set<UniqueId> trainingIds = bookedTrainingsByUser.stream()
                .map(MentorCalendar::getTrainingId)
                .collect(toSet());
        Set<MentorTraining> trainingsBooked = mentorTrainings.findByTrainingIds(trainingIds);
        Set<EmailAddress> addressesOfMentorsOfTrainingsBooked = trainingsBooked.stream()
                .map(MentorTraining::getMentorId)
                .collect(toSet());
        List<Mentor> mentorsBookedByUser = mentors.findAll(new ArrayList<>(addressesOfMentorsOfTrainingsBooked));
        Set<MentorTrainingDetails> trainingsDetailsBooked = new HashSet<>();
        mapToTrainingsDetails(trainingsBooked, mentorsBookedByUser, trainingsDetailsBooked, bookedTrainingsByUser);
        return trainingsDetailsBooked;
    }

    private void mapToTrainingsDetails(
            Set<MentorTraining> trainingsBooked, List<Mentor> mentorsBookedByUser,
            Set<MentorTrainingDetails> trainingsDetailsBooked, Set<MentorCalendar> bookedTrainingsByUser
    ) {
        for (MentorTraining trainingBooked : trainingsBooked) {
            mapTrainingDetails(mentorsBookedByUser, trainingsDetailsBooked, trainingBooked, bookedTrainingsByUser);
        }
    }

    private void mapTrainingDetails(
            List<Mentor> mentorsBookedByUser, Set<MentorTrainingDetails> trainingsDetailsBooked,
            MentorTraining bookedTraining, Set<MentorCalendar> bookedTrainingsByUser
    ) {
        trainingsDetailsBooked.add(mapTrainingDetails(
                bookedTraining, mentorsBookedByUser, bookedTrainingsByUser)
        );
    }

    private MentorTrainingDetails mapTrainingDetails(
            MentorTraining bookedTraining, List<Mentor> mentorsBookedByUser,
            Set<MentorCalendar> bookedTrainingsByUser
    ) {
        Mentor mentorOfTraining = mentorsBookedByUser.stream().filter(m -> bookedTraining.getMentorId().equals(m.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        MentorCalendar bookedTrainingCalendar = bookedTrainingsByUser.stream()
                .filter(t -> t.getTrainingId().equals(bookedTraining.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        Skill skill = skills.getOrThrow(bookedTraining.getSkillId());
        return new MentorTrainingDetails(mentorOfTraining, bookedTraining, bookedTrainingCalendar, skill);
    }
}
