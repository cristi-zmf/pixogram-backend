package com.cristi.pixogram.domain.userimage.update;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.UserImage;
import com.cristi.pixogram.domain.userimage.UserImages;
import org.springframework.stereotype.Service;

@Service
public class UpdateImageTitleAndDescription {
    private final UserImages userImages;

    public UpdateImageTitleAndDescription(UserImages userImages) {
        this.userImages = userImages;
    }

    public UniqueId updateTitleAndDescription(ImageIdentificationInfoUpdateCommand updateCommand) {
        UserImage image = userImages.getOrThrow(updateCommand.getImageId());
        image = image.updateIdentificationInfo(updateCommand);
        return userImages.add(image).getId();
    }
}
