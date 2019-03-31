package com.cristi.pixogram.domain.userimage.update;

import com.cristi.pixogram.domain.BaseValueObject;
import com.cristi.pixogram.domain.EmailAddress;
import com.cristi.pixogram.domain.UniqueId;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Arrays.asList;


public class ImageIdentificationInfoUpdateCommand extends BaseValueObject<ImageIdentificationInfoUpdateCommand> {
    @JsonProperty private final UniqueId imageId;
    @JsonProperty private final EmailAddress owner;
    @NotNull
    @JsonProperty private final String title;
    @NotNull
    @JsonProperty private final String description;

    public ImageIdentificationInfoUpdateCommand(UniqueId imageId, EmailAddress owner, String title, String description) {
        super(ImageIdentificationInfoUpdateCommand.class);
        this.imageId = imageId;
        this.owner = owner;
        this.title = title;
        this.description = description;
        validate(this);
    }

    public UniqueId getImageId() {
        return imageId;
    }
    public EmailAddress getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(imageId, owner, title, description);
    }

    //USED  BY JPA/JACKSON
    private ImageIdentificationInfoUpdateCommand() {
        super(ImageIdentificationInfoUpdateCommand.class);
        imageId = null;
        owner = null;
        title = null;
        description = null;
    }
}
