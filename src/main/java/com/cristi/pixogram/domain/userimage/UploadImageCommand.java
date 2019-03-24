package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.exposition.UploadImageDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageCommand {
    public MultipartFile multipartFile;
    public UploadImageDetailsDto imageDetails;
}
