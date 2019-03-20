package com.cristi.pixogram.domain.userimage;

import org.junit.Test;

import java.io.File;

public class PathTest {

    @Test
    public void testPath() {
        System.out.println(System.getProperty("user.dir"));
        File currentDir = new File(System.getProperty("user.dir") + "/image_storage/john.john.com");
        System.out.println(currentDir.mkdir());
        System.out.println(currentDir.canWrite());
    }
}
