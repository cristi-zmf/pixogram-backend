package com.cristi.pixogram.infra.dataset;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.userimage.ImageService;
import com.cristi.pixogram.domain.userimage.UserImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Profile("DATASET")
public class DatasetRunner implements ApplicationRunner {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserImages userImages;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(classLoader.getResource("static/test_upload.jpg").getFile());
        EmailAddress someUsername = new EmailAddress("test@test.com");
        imageService.uploadImageInternally(img, someUsername);
    }
}
