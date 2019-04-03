package com.cristi.pixogram.exposition.userimage;

import com.cristi.pixogram.domain.UniqueId;
import com.cristi.pixogram.domain.userimage.update.ImageIdentificationInfoUpdateCommand;
import com.cristi.pixogram.domain.userimage.update.UpdateImageTitleAndDescription;
import com.cristi.pixogram.exposition.PixogramBaseRequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@PixogramBaseRequestMapping
public class UpdateImageTitleAndDescriptionResource {
    private final UpdateImageTitleAndDescription updateImageTitleAndDescription;


    public UpdateImageTitleAndDescriptionResource(UpdateImageTitleAndDescription updateImageTitleAndDescription) {
        this.updateImageTitleAndDescription = updateImageTitleAndDescription;
    }

    @PostMapping("/update-identification-info")
    public ResponseEntity<UniqueId> updateTitleAndDescription(@Valid @RequestBody ImageIdentificationInfoUpdateCommand command) {
        return ResponseEntity.ok(updateImageTitleAndDescription.updateTitleAndDescription(command));
    }
}
