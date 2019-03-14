package com.cristi.mentool.mentool.infra.persistence.mentor;

import com.cristi.mentool.mentool.domain.mentor.MentorSearchResult;
import com.cristi.mentool.mentool.domain.mentor.MentorTrainingSearch;
import com.cristi.mentool.mentool.infra.dataset.SkillsDataset;
import com.cristi.mentool.mentool.infra.persistence.IntegrationTestWithDataset;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MentorTrainingSearch_WithDataSetLocalIT extends IntegrationTestWithDataset {
    @Autowired
    private MentorTrainingSearch sut;


    @Test
    public void should_return_1_result_for_java_and_no_dates() {
        List<MentorSearchResult> results = sut.searchForMentors(
                SkillsDataset.PHP.getSkillName(), null, null
        );
        assertThat(results).hasSize(1);
    }

    @Test
    public void should_return_2_results_for_microservices_and_no_dates() {
        List<MentorSearchResult> results = sut.searchForMentors(
                SkillsDataset.MICROSERVICES.getSkillName(), null, null
        );
        assertThat(results).hasSize(2);
    }
}
