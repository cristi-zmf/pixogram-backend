package com.cristi.pixogram.exposition.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditCommentCommandDto {
    @JsonProperty public String id;
    @JsonProperty public String value;
    @JsonProperty public String author;
    @JsonProperty public String imageId;
}
