package com.cristi.pixogram.exposition;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.UserImage;
import com.cristi.pixogram.domain.userimage.UserImages;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@MentoolRequestMapping
public class GetImagesResource {
    private final UserImages userImages;

    public GetImagesResource(UserImages userImages) {
        this.userImages = userImages;
    }

    @GetMapping("/{username}/images/ids")
    public Set<UniqueId> getImagesIds(@PathVariable String username) {
        Set<UniqueId> imageIds = userImages.findAllByUsername(new EmailAddress(username)).stream()
                .map(UserImage::getId)
                .collect(toSet());
        return imageIds;
    }

    @GetMapping("/images/thumbnails/{imageId}")
    public ResponseEntity<InputStreamResource> getImageThumbnail(@PathVariable String imageId) throws FileNotFoundException {
        UserImage image = userImages.getOrThrow(new UniqueId(imageId));
        File imageFile = new File(image.getImageThumbnailPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new FileInputStream(imageFile)));
    }
}
