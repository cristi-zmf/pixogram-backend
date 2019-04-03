package com.cristi.pixogram.exposition.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDislikeCommandDto {
    @JsonProperty public String id;
    @JsonProperty public String author;
}
