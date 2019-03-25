package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.upload.ImageValidator;
import com.cristi.pixogram.domain.userimage.upload.ThumbnailGenerator;
import com.cristi.pixogram.domain.userimage.upload.UploadImageCommand;
import com.cristi.pixogram.domain.userimage.upload.UserImagePathProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.String.format;

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

    public UniqueId uploadImage(UploadImageCommand uploadImageCommand)  {
        MultipartFile multipartImage = uploadImageCommand.multipartFile;
        validator.validateImage(multipartImage);
        EmailAddress username = uploadImageCommand.imageDetails.username;
        String filePath = pathProvider.generatePathForNewFile(multipartImage.getOriginalFilename(), username);
        File newImage = new File(filePath);
        return tryToUploadFile(uploadImageCommand, multipartImage, username, newImage);
    }


    public UniqueId uploadImageInternally(
            File imageFile, EmailAddress username, String title, String description
    ) throws IOException {
        String filePath = pathProvider.generatePathForNewFile(imageFile.getName(), username);
        File newImage = new File(filePath);
        FileInputStream initialFileStream = new FileInputStream(imageFile);
        byte[] imageBytes = new byte[initialFileStream.available()];
        initialFileStream.read(imageBytes);
        newImage.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(newImage);
        outputStream.write(imageBytes);
        String thumbnailPath = thumbnailGenerator.generateThumbnail(newImage);
        UserImage userImage = new UserImage(
                filePath, thumbnailPath, username, title, description
        );
        return userImages.add(userImage).getId();
    }

    private UniqueId tryToUploadFile(
            UploadImageCommand uploadImageCommand, MultipartFile multipartImage, EmailAddress username, File newImage
    ) {
        String thumbnailPath = "";
        try {
            multipartImage.transferTo(newImage);
            thumbnailPath = thumbnailGenerator.generateThumbnail(newImage);
            UserImage userImage = new UserImage(
                    newImage.getAbsolutePath(), thumbnailPath, username,
                    uploadImageCommand.imageDetails.imageTitle, uploadImageCommand.imageDetails.imageDescription
            );
            return userImages.add(userImage).getId();
        } catch (Exception e) {
            newImage.delete();
            if (!thumbnailPath.equals("")) {
                new File(thumbnailPath).delete();
            }
            throw new RuntimeException(format("Could not upload file %s. Tried to delete partial files...", newImage), e);
        }
    }
}
