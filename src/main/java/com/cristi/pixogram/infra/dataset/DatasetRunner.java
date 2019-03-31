package com.cristi.pixogram.infra.dataset;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.userimage.DeleteAllFilesInImageRepo;
import com.cristi.pixogram.domain.userimage.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Component
@Profile("DATASET")
@Transactional
public class DatasetRunner implements ApplicationRunner {
    private final ImageService imageService;
    private final DeleteAllFilesInImageRepo deleteAllFilesInImageRepo;
    @Value("${files.location.storage}")
    private String storageLocation;

    public DatasetRunner(ImageService imageService, DeleteAllFilesInImageRepo deleteAllFilesInImageRepo) {
        this.imageService = imageService;
        this.deleteAllFilesInImageRepo = deleteAllFilesInImageRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        deleteAllFilesInImageRepo.deleteAllFoldersAndFilesInStorageLocation();
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(classLoader.getResource("static/test_upload.jpg").getFile());
        EmailAddress someUsername = UserJohn.USERNAME;
        imageService.uploadImageInternally(img, someUsername, "Cool image", "Cool description");
    }
}
