package com.cristi.pixogram.exposition.comment;

import com.cristi.pixogram.domain.EmailAddress;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCommentCommandDto {
    @JsonProperty public String value;
    @JsonProperty public String author;
    @JsonProperty public String imageId;
}
