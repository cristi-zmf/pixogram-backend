package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.infra.persistence.IntegrationTestWithNoDataset;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ImageServiceLocalIT extends IntegrationTestWithNoDataset {
    @Autowired
    private ImageService sut;

    @Test
    public void uploadImage() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(classLoader.getResource("static/test_upload.jpg").getFile());
        MultipartFile multipartFile = new MockMultipartFile(img.getName(), img.getName(), "multipart/mixed", Files.readAllBytes(img.toPath()));
        sut.uploadImage(new UploadImageCommand(new EmailAddress("test@test.com"), multipartFile));
    }
}
