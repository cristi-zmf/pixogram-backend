package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.UserImage;
import com.cristi.pixogram.domain.userimage.UserImages;
import com.cristi.pixogram.domain.userimage.update.ImageIdentificationInfoUpdateCommand;
import com.cristi.pixogram.exposition.BaseExpoDatasetTest;
import com.cristi.pixogram.infra.dataset.DatasetRunner;
import com.cristi.pixogram.infra.dataset.UserJohn;
import com.cristi.pixogram.infra.userimage.UserImagesSdj;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore//something weird is happening with the jpa repository here, it is not persisting
public class UpdateImageTitleAndDescriptionResourceLocalIT extends BaseExpoDatasetTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserImagesSdj userImages;
    @Autowired
    private DatasetRunner datasetRunner;

//    @Before
//    public void insertDataset() throws Exception {
//        datasetRunner.run(null);
//    }

    @Test
    public void updateTitleAndDescription_should_not_crash_when_valid_command() throws JsonProcessingException {
        UserImage johnImage = userImages.findAllByUsername(UserJohn.USERNAME).stream()
                .findFirst().orElseThrow(NoSuchElementException::new);
        ImageIdentificationInfoUpdateCommand validCommand = new ImageIdentificationInfoUpdateCommand(
                johnImage.getId(), UserJohn.USERNAME, "new title", "new description"
        );
        ResponseEntity<UniqueId> updatedImageId = restTemplate.postForEntity(
                createURLWithPort("/images/update-identification-info"),
                new IdentificationInfoUpdateCommandDto(validCommand), UniqueId.class
        );
//        UserImage modifiedImage = userImages.getOrThrow(updatedImageId.getBody());
//        assertThat(updatedImageId.getBody()).isEqualTo(johnImage.getId());
//        assertThat(modifiedImage.getImageTitle()).isEqualTo(validCommand.getTitle());
//        assertThat(modifiedImage.getImageDescription()).isEqualTo(validCommand.getDescription());
    }

    @After
    public void testChanges() {
        UserImage modifiedImage = userImages.findAllByUsername(UserJohn.USERNAME).stream().findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(modifiedImage.getImageTitle()).isEqualTo("new title");
        assertThat(modifiedImage.getImageDescription()).isEqualTo("new description");
    }

    private class IdentificationInfoUpdateCommandDto {
        @JsonProperty private String owner;
        @JsonProperty private String imageId;
        @JsonProperty private String title;
        @JsonProperty private String description;

        public IdentificationInfoUpdateCommandDto(ImageIdentificationInfoUpdateCommand command) {
            owner = command.getOwner().getValue();
            imageId = command.getImageId().getValue();
            title = command.getTitle();
            description = command.getDescription();
        }

        public IdentificationInfoUpdateCommandDto() {
        }
    }
}
