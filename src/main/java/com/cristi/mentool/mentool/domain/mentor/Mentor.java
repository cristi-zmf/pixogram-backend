package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.BaseUser;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Access(AccessType.FIELD)
@AttributeOverride(name = "id", column = @Column(name = "MENTOR_ID"))
@NoArgsConstructor(access = PRIVATE)
@Getter
public class Mentor extends BaseUser {
    @Column(name = "YEARS_OF_EXPERIENCE")
    private int yearsOfExperience;

    @Column(name = "LINKEDIN_URL")
    private String linkedinUrl;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "MENTOR_ID")
    @NotNull
    @JsonIgnore
    private Set<MentorTraining> trainings;

    private int noOfOverallTrainingsDone;


    public Mentor(
            @NotNull EmailAddress username, @NotBlank String firstName, @NotBlank String lastName,
            @NotNull PhoneNumber phoneNumber,
            int yearsOfExperience, String linkedinUrl, Set<MentorTraining> trainings,
            int noOfOverallTrainingsDone
    ) {
        super(username, firstName, lastName, phoneNumber);
        this.yearsOfExperience = yearsOfExperience;
        this.linkedinUrl = linkedinUrl;
        this.trainings = new HashSet<>(trainings);
        this.noOfOverallTrainingsDone = noOfOverallTrainingsDone;
        validate(this);
    }

    public Optional<String> getLinkedinUrl() {
        return Optional.ofNullable(linkedinUrl);
    }

    public Set<MentorTraining> getTrainings() {
        return new HashSet<>(trainings);
    }

    public boolean addTraining(MentorTraining training) {
        return trainings.add(training);
    }

    public void removeTrainings(Set<UniqueId> trainingIds) {
        trainings.removeAll(trainings.stream()
                .filter(t -> trainingIds.contains(t.getId()))
                .collect(toSet()));
    }

    public Optional<MentorTraining> getTraining(UniqueId trainingId) {
        return trainings.stream().filter(t -> t.getId().equals(trainingId))
                .findFirst();
    }
}
