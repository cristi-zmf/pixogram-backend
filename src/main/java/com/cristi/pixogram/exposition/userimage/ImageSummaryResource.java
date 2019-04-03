package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.ImageSummaryResolver;
import com.cristi.pixogram.exposition.PixogramBaseRequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;

@PixogramBaseRequestMapping
public class ImageSummaryResource {
    private final ImageSummaryResolver imageSummaryResolver;

    public ImageSummaryResource(ImageSummaryResolver imageSummaryResolver) {
        this.imageSummaryResolver = imageSummaryResolver;
    }

    @GetMapping("/summaries/{imageId}")
    public ImageSummaryDto getImageSummary(@PathVariable String imageId) throws FileNotFoundException {
        return imageSummaryResolver.resolveSummary(new UniqueId(imageId));
    }
}
