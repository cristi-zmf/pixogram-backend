package com.cristi.pixogram.exposition;

import com.cristi.pixogram.domain.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageDetailsDto {
    @JsonProperty public EmailAddress username;
    @JsonProperty public String imageTitle;
    @JsonProperty public String imageDescription;
}
