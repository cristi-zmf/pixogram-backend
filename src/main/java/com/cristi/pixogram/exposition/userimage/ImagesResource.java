package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.*;
import com.cristi.pixogram.domain.userimage.upload.UploadImageCommand;
import com.cristi.pixogram.exposition.PixogramBaseRequestMapping;
import com.cristi.pixogram.exposition.comment.LikeDislikeCommandDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.http.ResponseEntity.ok;

@PixogramBaseRequestMapping
public class ImagesResource {
    private final UserImages userImages;
    private final ImageService imageService;
    private final LikeDislikeImage likeDislikeImage;

    public ImagesResource(UserImages userImages, ImageService imageService, LikeDislikeImage likeDislikeImage) {
        this.userImages = userImages;
        this.imageService = imageService;
        this.likeDislikeImage = likeDislikeImage;
    }

    @GetMapping("/{username}/images/id-titles")
    public Set<ImageIdTitleDto> getImagesIdTitles(@PathVariable String username) {
        Set<ImageIdTitleDto> imageIdTitles = userImages.findAllByUsername(new EmailAddress(username)).stream()
                .map(i -> new ImageIdTitleDto(i.getId(), i.getImageTitle()))
                .collect(toSet());
        return imageIdTitles;
    }

    @PutMapping("/like-image")
    public ResponseEntity<UniqueId> likeImage(@RequestBody LikeDislikeCommandDto command) {
        return ok(likeDislikeImage.likeImage(command));
    }

    @PutMapping("/dislike-image")
    public ResponseEntity<UniqueId> dislikeImage(@RequestBody LikeDislikeCommandDto command) {
        return ok(likeDislikeImage.dislikeImage(command));
    }

    @GetMapping("/thumbnails/{imageId}")
    public ResponseEntity<InputStreamResource> getImageThumbnail(@PathVariable String imageId) throws FileNotFoundException {
        UserImage image = userImages.getOrThrow(new UniqueId(imageId));
        File imageFile = new File(image.getImageThumbnailPath());
        return ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new FileInputStream(imageFile)));
    }

    @GetMapping("/full-images/{imageId}")
    public ResponseEntity<InputStreamResource> getFullImage(@PathVariable String imageId) throws FileNotFoundException {
        UserImage image = userImages.getOrThrow(new UniqueId(imageId));
        File imageFile = new File(image.getImagePath());
        return ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new FileInputStream(imageFile)));
    }

    @PostMapping(value = "/full-images", consumes={"multipart/form-data", MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<UniqueId> getFullImage(
            @RequestPart("multipartFile") MultipartFile multipartFile,
            @RequestParam("imageDetails") String imageDetailsMap
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UploadImageDetailsDto imageDetails = objectMapper.readValue(imageDetailsMap, UploadImageDetailsDto.class);
        UploadImageCommand command = new UploadImageCommand(multipartFile, imageDetails);
        UniqueId imageId = imageService.uploadImage(command);
        return ok(imageId);
    }
}
