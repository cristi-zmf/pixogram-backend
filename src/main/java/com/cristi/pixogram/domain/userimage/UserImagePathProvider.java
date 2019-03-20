package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;

@Service
public class UserImagePathProvider {
    @Value("${files.location.storage}")
    private String storage;

    public String generatePathForNewFile(String originalFilename, EmailAddress username) {
        String timestamp = getUploadedFileTimestamp();
        int indexOfLastDot = originalFilename.lastIndexOf(".");
        String normalizedFilename = normalizeFileName(originalFilename, timestamp, indexOfLastDot);
        String userStorageFolder = storage + '/' + username.getValue().replace("@", ".");
        File userFolder = new File(new File(userStorageFolder).getAbsolutePath());
        tryToCreateUserFolder(userFolder);
        return userStorageFolder + '/' + normalizedFilename;
    }

    private String normalizeFileName(String originalFilename, String timestamp, int indexOfLastDot) {
        return originalFilename.substring(0, indexOfLastDot) + '_' + timestamp + originalFilename.substring(indexOfLastDot);
    }

    private String getUploadedFileTimestamp() {
        LocalDateTime uniqueId = LocalDateTime.now();
        return uniqueId.toString().replace(":", "-");
    }

    private void tryToCreateUserFolder(File userFolder) {
        if (!userFolder.exists()) {
            if (!userFolder.mkdirs()) {
                throw new IllegalStateException("Could not create  folder: " + userFolder);
            }
        }
    }
}
