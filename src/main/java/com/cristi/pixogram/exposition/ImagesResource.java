package com.cristi.pixogram.exposition;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.ImageService;
import com.cristi.pixogram.domain.userimage.UploadImageCommand;
import com.cristi.pixogram.domain.userimage.UserImage;
import com.cristi.pixogram.domain.userimage.UserImages;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@MentoolRequestMapping
public class ImagesResource {
    private final UserImages userImages;
    private final ImageService imageService;

    public ImagesResource(UserImages userImages, ImageService imageService) {
        this.userImages = userImages;
        this.imageService = imageService;
    }

    @GetMapping("/{username}/images/ids")
    public Set<UniqueId> getImagesIds(@PathVariable String username) {
        Set<UniqueId> imageIds = userImages.findAllByUsername(new EmailAddress(username)).stream()
                .map(UserImage::getId)
                .collect(toSet());
        return imageIds;
    }

    @GetMapping("/thumbnails/{imageId}")
    public ResponseEntity<InputStreamResource> getImageThumbnail(@PathVariable String imageId) throws FileNotFoundException {
        UserImage image = userImages.getOrThrow(new UniqueId(imageId));
        File imageFile = new File(image.getImageThumbnailPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new FileInputStream(imageFile)));
    }

    @GetMapping("/full-image/{imageId}")
    public ResponseEntity<InputStreamResource> getFullImage(@PathVariable String imageId) throws FileNotFoundException {
        UserImage image = userImages.getOrThrow(new UniqueId(imageId));
        File imageFile = new File(image.getImagePath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new FileInputStream(imageFile)));
    }

    @PostMapping(value = "/full-images", consumes="multipart/form-data")
    public ResponseEntity<UniqueId> getFullImage(
            @RequestPart("multipartFile") MultipartFile multipartFile, @RequestPart("username") String username
    ) throws IOException {
        UploadImageCommand command = new UploadImageCommand(new EmailAddress(username), multipartFile);
        UniqueId imageId = imageService.uploadImage(command);
        return ResponseEntity.ok(imageId);
    }
}
