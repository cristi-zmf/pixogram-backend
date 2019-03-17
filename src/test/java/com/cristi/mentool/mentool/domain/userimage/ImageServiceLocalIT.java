package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.infra.persistence.IntegrationTestWithNoDataset;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ImageServiceLocalIT extends IntegrationTestWithNoDataset {
    @Autowired
    private ImageService sut;
    @Autowired
    private UserImages userImages;

    @Test
    public void uploadImage() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(classLoader.getResource("static/test_upload.jpg").getFile());
        MultipartFile multipartFile = new MockMultipartFile(img.getName(), img.getName(), "multipart/mixed", Files.readAllBytes(img.toPath()));
        EmailAddress someUsername = new EmailAddress("test@test.com");
        assertThat(userImages.findAllByUsername(someUsername)).isEmpty();
        sut.uploadImage(new UploadImageCommand(someUsername, multipartFile));
        UserImage savedImage = verifyImageWasSavedInUserImageRepo(someUsername);
        File userFolder = verifyImagesWereUploadedInUserFolder(savedImage);
        deleteTestFolderAndFiles(userFolder);
    }

    private void deleteTestFolderAndFiles(File userFolder) {
        Arrays.stream(userFolder.listFiles()).forEach(File::delete);
        if (!userFolder.delete()) {
            throw new IllegalStateException(
                    "Folder " + userFolder.getAbsolutePath() + "can not be deleted. Try to delete it manually"
            );
        }
    }

    private File verifyImagesWereUploadedInUserFolder(UserImage savedImage) {
        String pathToSavedImage = savedImage.getImagePath();
        String pathToUserFolder = pathToSavedImage.substring(0, pathToSavedImage.lastIndexOf("/"));
        File userFolder = new File(pathToUserFolder);
        assertThat(userFolder).isDirectory();
        assertThat(userFolder).exists();
        File savedFile = new File(pathToSavedImage);
        File savedThumbnail = new File(savedImage.getImageThumbnailPath());
        assertThat(savedFile.length()).isGreaterThan(savedThumbnail.length());
        return userFolder;
    }

    private UserImage verifyImageWasSavedInUserImageRepo(EmailAddress someUsername) {
        Set<UserImage> savedImages = userImages.findAllByUsername(someUsername);
        assertThat(savedImages).hasSize(1);
        return savedImages.stream().findFirst().orElseThrow(NoSuchElementException::new);
    }
}
