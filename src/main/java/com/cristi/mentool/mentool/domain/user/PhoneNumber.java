package com.cristi.mentool.mentool.domain.user;

import com.cristi.mentool.mentool.domain.BaseValueObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Embeddable
@Getter
@JsonSerialize(using = PhoneNumber.Serializer.class)
@JsonDeserialize(using = PhoneNumber.Deserializer.class)
public class PhoneNumber extends BaseValueObject<PhoneNumber> {
    @NotBlank @Pattern(regexp="(^$|[0-9]{10})") @Column(name = "PHONE_NUMBER")
    private final String value;

    public PhoneNumber(String value) {
        super(PhoneNumber.class);
        this.value = value;
        validate(this);
    }

    private PhoneNumber() {
        super(PhoneNumber.class);
        this.value = null;
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return Collections.singletonList(value);
    }

    public static class Serializer extends JsonSerializer<PhoneNumber> {
        @Override
        public void serialize(PhoneNumber phoneNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(phoneNumber.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<PhoneNumber> {
        @Override
        public PhoneNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new PhoneNumber(jsonParser.getText());
        }
    }
}
