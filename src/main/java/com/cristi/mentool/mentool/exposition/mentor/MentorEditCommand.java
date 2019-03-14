package com.cristi.mentool.mentool.exposition.mentor;

import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorEditCommand {
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String username;
    @JsonProperty
    private String phoneNumber;
    @JsonProperty
    private int yearsOfExperience;
    @JsonProperty
    private String linkedinUrl;

    public Mentor toNewMentor() {
        return new Mentor(
                new EmailAddress(username), firstName, lastName, new PhoneNumber(phoneNumber),
                yearsOfExperience, linkedinUrl, new HashSet<>(), 0
        );
    }
}
