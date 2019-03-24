package com.cristi.pixogram.exposition;

import com.cristi.pixogram.domain.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UploadImageDetailsDto {
    @JsonProperty public EmailAddress username;
    @JsonProperty public String imageTitle;
    @JsonProperty public String imageDescription;
}
