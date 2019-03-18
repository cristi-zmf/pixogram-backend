package com.cristi.pixogram.domain.userimage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;

import static java.lang.String.format;

@Service
public class DeleteAllFilesInImageRepo {
    @Value("${files.location.storage}")
    private String storageLocation;

    public void deleteAllFoldersAndFilesInStorageLocation() {
        File storageFolder = new File(storageLocation);
        if (storageFolder.listFiles() != null) {
            for (File file : storageFolder.listFiles()) {
                if (!FileSystemUtils.deleteRecursively(file)) {
                    throw new IllegalStateException(format("Could not delete %s. Try delete it manually", file));
                }
            }
        }
    }
}
