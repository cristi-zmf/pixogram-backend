package com.cristi.mentool.mentool.domain.user;

import com.cristi.mentool.mentool.domain.BaseValueObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Embeddable
@JsonSerialize(using = EmailAddress.Serializer.class)
@JsonDeserialize(using = EmailAddress.Deserializer.class)
public class EmailAddress extends BaseValueObject<EmailAddress> implements Serializable {
    @Email @NotBlank @Column(name = "EMAIL")
    private final String value;

    public EmailAddress(String value) {
        super(EmailAddress.class);
        this.value = value;
        validate(this);
    }

    public String getValue() {
        return value;
    }

    /*USED ONLY BY JPA*/
    private EmailAddress() {
        super(EmailAddress.class);
        this.value = null;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return Collections.singletonList(value);
    }

    public static class Serializer extends JsonSerializer<EmailAddress> {
        @Override
        public void serialize(
                EmailAddress emailAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeString(emailAddress.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<EmailAddress> {
        @Override
        public EmailAddress deserialize(
                JsonParser jsonParser, DeserializationContext deserializationContext
        ) throws IOException {
            return new EmailAddress(jsonParser.getText());
        }
    }
}
