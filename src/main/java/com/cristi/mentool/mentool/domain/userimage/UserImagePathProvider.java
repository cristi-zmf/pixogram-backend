package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import com.cristi.mentool.mentool.domain.UniqueId;
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
        String userStorageFolder = storage + '/' + username.getValue();
        File userFolder = new File(userStorageFolder);
        tryToCreateUserFolder(username, userFolder);
        return userStorageFolder + '/' + normalizedFilename;
    }

    private String normalizeFileName(String originalFilename, String timestamp, int indexOfLastDot) {
        return originalFilename.substring(0, indexOfLastDot) + '_' + timestamp + originalFilename.substring(indexOfLastDot);
    }

    private String getUploadedFileTimestamp() {
        LocalDateTime uniqueId = LocalDateTime.now();
        return uniqueId.toString().replace(":", "-");
    }

    private void tryToCreateUserFolder(EmailAddress username, File userFolder) {
        if (!userFolder.exists()) {
            if (!userFolder.mkdir()) {
                throw new IllegalStateException("Could not create user folder for user " + username);
            }
        }
    }
}
