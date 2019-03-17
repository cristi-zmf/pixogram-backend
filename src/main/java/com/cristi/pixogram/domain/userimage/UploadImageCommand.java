package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageCommand {
    public EmailAddress username;
    public MultipartFile multipartFile;
}
