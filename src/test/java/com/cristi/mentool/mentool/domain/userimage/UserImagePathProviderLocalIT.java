package com.cristi.mentool.mentool.domain.userimage;

import com.cristi.mentool.mentool.domain.EmailAddress;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserImagePathProviderLocalIT {
    @Autowired
    private UserImagePathProvider sut;

    @Value("${files.location.storage}")
    private String storage;

    @Test
    public void generatePathForNewFile() {
        EmailAddress username = new EmailAddress("uploadtest@test.com");
        String pathForNewFile = sut.generatePathForNewFile("test.jpeg", username);
        File userFolder = new File(storage + "/" + username.getValue());
        assertThat(userFolder).exists();
        assertThat(userFolder).isDirectory();
        assertThat(pathForNewFile).startsWith(userFolder + "/" + "test");
        if (!userFolder.delete()) {
         throw new IllegalStateException(
                 "Test folder can not be deleted after test ran. Try to delete it manually at: " + userFolder.getAbsolutePath()
         );
        }
        System.out.println(pathForNewFile);
    }
}
