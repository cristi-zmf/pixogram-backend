package com.cristi.pixogram.exposition.comment;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDislikeCommandDto {
    @JsonProperty public String id;
    @JsonProperty public String author;

    public UniqueId domainId() {
        return new UniqueId(id);
    }

    public EmailAddress domainAuthor() {
        return new EmailAddress(author);
    }
}
