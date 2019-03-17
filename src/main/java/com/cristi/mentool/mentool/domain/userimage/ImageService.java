package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private final UserImages userImages;
    private final UserImagePathProvider pathProvider;
    private final ThumbnailGenerator thumbnailGenerator;
    private final ImageValidator validator;

    public ImageService(
            UserImages userImages, UserImagePathProvider pathProvider,
            ThumbnailGenerator thumbnailGenerator, ImageValidator validator
    ) {
        this.userImages = userImages;
        this.pathProvider = pathProvider;
        this.thumbnailGenerator = thumbnailGenerator;
        this.validator = validator;
    }

    public UniqueId uploadImage(UploadImageCommand uploadImageCommand) throws IOException {
        MultipartFile multipartImage = uploadImageCommand.multipartFile;
        validator.validateImage(multipartImage);
        EmailAddress username = uploadImageCommand.username;
        String filePath = pathProvider.generatePathForNewFile(multipartImage.getOriginalFilename(), username);
        File newImage = new File(filePath);
        multipartImage.transferTo(newImage);
        String thumbnailPath = thumbnailGenerator.generateThumbnail(newImage);
        UserImage userImage = new UserImage(filePath, thumbnailPath, username);
        return userImages.add(userImage).getId();
    }
}
