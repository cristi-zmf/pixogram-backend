package com.cristi.pixogram.domain.userimage;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ThumbnailGenerator {
    public String generateThumbnail(File image) throws IOException {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(
                ImageIO.read(image).getScaledInstance(100, 100, Image.SCALE_SMOOTH),
                0,0,null
        );
        String thumbnailPath = image.getAbsolutePath();
        int indexOfDotBeforeExtension = thumbnailPath.lastIndexOf(".");
        thumbnailPath = thumbnailPath.substring(0, indexOfDotBeforeExtension) + "_thumb"
                + thumbnailPath.substring(indexOfDotBeforeExtension);
        ImageIO.write(img, "jpg", new File(thumbnailPath));
        return thumbnailPath;
    }
}
