package com.cristi.mentool.mentool.exposition.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCreateCommand {
    @JsonProperty
    public String skillName;
}
