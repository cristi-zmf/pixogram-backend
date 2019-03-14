package com.cristi.mentool.mentool.exposition.mentor;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingsRemovalCommand {
    @JsonProperty
    @NotNull
    public EmailAddress mentorAddress;
    @JsonProperty
    @NotEmpty
    public Set<UniqueId> trainingIdsToBeRemoved;
}
