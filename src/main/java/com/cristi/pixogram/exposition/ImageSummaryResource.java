package com.cristi.pixogram.exposition;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.ImageSummaryResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;

@MentoolRequestMapping
public class ImageSummaryResource {
    private final ImageSummaryResolver imageSummaryResolver;

    public ImageSummaryResource(ImageSummaryResolver imageSummaryResolver) {
        this.imageSummaryResolver = imageSummaryResolver;
    }

    @GetMapping("/summaries/{imageId}")
    public ImageSummaryDto getImageThumbnail(@PathVariable String imageId) throws FileNotFoundException {
        return imageSummaryResolver.resolveSummary(new UniqueId(imageId));
    }
}
