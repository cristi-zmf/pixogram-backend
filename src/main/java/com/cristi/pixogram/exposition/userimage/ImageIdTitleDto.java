package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageIdTitleDto {
    @JsonProperty public UniqueId id;
    @JsonProperty public String title;
}
