package com.cristi.mentool.mentool.domain.mentor.calendar;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCommand {
    @JsonProperty
    @NotNull
    public UniqueId trainingId;
    @JsonProperty
    @NotNull
    public EmailAddress traineeEmail;
}
