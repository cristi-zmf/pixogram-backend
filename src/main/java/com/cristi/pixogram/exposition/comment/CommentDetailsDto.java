package com.cristi.pixogram.exposition.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailsDto {
    @JsonProperty public String id;
    @JsonProperty public String value;
    @JsonProperty public String author;
    @JsonProperty public String imageId;
    @JsonProperty public Set<String> likes;
    @JsonProperty public Set<String> dislikes;
    @JsonProperty public LocalDateTime lastModified;
}
