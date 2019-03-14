package com.cristi.mentool.mentool.domain.mentor;

import com.cristi.mentool.mentool.domain.BaseValueObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class MentorSearchResult extends BaseValueObject<MentorSearchResult> {
    @NotNull
    @JsonProperty
    private final String firstName;
    @NotNull
    @JsonProperty
    private final String lastName;
    @JsonProperty
    @NotEmpty
    private final String skillName;
    @JsonProperty
    private final int yearsOfExperience;
    @JsonProperty
    private final int noOfOverallTrainingsDone;
    @JsonProperty
    private final int noOfTrainingsForTechnologyDone;
    @JsonProperty
    private final String trainingId;


    @NotNull
    @JsonProperty
    @JsonSerialize(contentUsing = BigDecimalSerializer.class)
    private final BigDecimal fee;

    public MentorSearchResult(
            String firstName, String lastName, int yearsOfExperience, int noOfOverallTrainingsDone,
            int noOfTrainingsForTechnologyDone, BigDecimal fee, String skillName, String trainingId) {
        super(MentorSearchResult.class);
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillName = skillName;
        this.yearsOfExperience = yearsOfExperience;
        this.noOfOverallTrainingsDone = noOfOverallTrainingsDone;
        this.noOfTrainingsForTechnologyDone = noOfTrainingsForTechnologyDone;
        this.fee = fee;
        this.trainingId = trainingId;
        validate(this);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(
                firstName, lastName, yearsOfExperience,
                noOfOverallTrainingsDone, noOfTrainingsForTechnologyDone,
                fee, trainingId
        );
    }

    private class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
        @Override
        public void serialize(BigDecimal o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(o.toString());
        }
    }
}
