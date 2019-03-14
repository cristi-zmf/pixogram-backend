package com.cristi.mentool.mentool.domain.mentor.calendar;


import com.cristi.mentool.mentool.domain.BaseEntity;
import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Access(AccessType.FIELD)
public class MentorCalendar extends BaseEntity<MentorCalendar, UniqueId> {
    @NotNull
    @AttributeOverride(name = "value", column = @Column(name = "TRAINING_ID"))
    private UniqueId trainingId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private int maximumNumberOfTrainees;

    @ElementCollection
    private Set<EmailAddress> traineesBooked;


    public MentorCalendar(
            @NotNull UniqueId id, UniqueId trainingId, @NotNull LocalDateTime startTime,
            @NotNull LocalDateTime endTime, int maximumNumberOfTrainees,
            Set<EmailAddress> traineesBooked) {
        super(MentorCalendar.class, id);
        this.trainingId = trainingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maximumNumberOfTrainees = maximumNumberOfTrainees;
        this.traineesBooked = traineesBooked;
        validate(this);
    }

    public Set<EmailAddress> getTraineesBooked() {
        return new HashSet<>(traineesBooked);
    }

    //Required by jpa
    private MentorCalendar() {
        super(MentorCalendar.class, new UniqueId());
        traineesBooked = new HashSet<>();
    }

    boolean bookForTrainingIfSpotsLeft(EmailAddress traineeEmail) {
        if (!anyFreeSpots()) {
            return false;
        } else {
            traineesBooked.add(traineeEmail);
            return true;
        }
    }

    private boolean anyFreeSpots() {
        return traineesBooked.size() < maximumNumberOfTrainees;
    }

    public boolean cancelBookingForTrainee(EmailAddress traineeEmail) {
        return traineesBooked.remove(traineeEmail);
    }
}
