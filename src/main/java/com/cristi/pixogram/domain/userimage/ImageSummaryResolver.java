package com.cristi.pixogram.domain.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.exposition.userimage.ImageSummaryDto;
import org.springframework.stereotype.Service;

import static com.cristi.pixogram.exposition.userimage.ImageSummaryDto.fromUserImage;

@Service
public class ImageSummaryResolver {
    private final UserImages userImages;

    public ImageSummaryResolver(UserImages userImages) {
        this.userImages = userImages;
    }

    public ImageSummaryDto resolveSummary(UniqueId id) {
        return fromUserImage(userImages.getOrThrow(id));
    }
}
