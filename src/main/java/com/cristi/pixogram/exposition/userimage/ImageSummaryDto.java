package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.UserImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.Collections.emptySet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageSummaryDto {
    @JsonProperty public UniqueId id;
    @JsonProperty public String title;
    @JsonProperty public String description;
    @JsonProperty public EmailAddress owner;
    @JsonProperty public Set<String> comments; //for now empty
    @JsonProperty public Set<EmailAddress> likes;//for now empty
    @JsonProperty public Set<EmailAddress> dislikes; //for now empty


    public static ImageSummaryDto fromUserImage(UserImage userImage) {
        return new ImageSummaryDto(
                userImage.getId(), userImage.getImageTitle(), userImage.getImageDescription(),
                userImage.getUsername(), emptySet(), userImage.getLikes(), userImage.getDislikes()
        );
    }
}
