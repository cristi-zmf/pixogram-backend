package com.cristi.mentool.mentool.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@Embeddable
@Access(AccessType.FIELD)
@JsonSerialize(using = UniqueId.Serializer.class)
@JsonDeserialize(using = UniqueId.Deserializer.class)
public class UniqueId extends BaseValueObject<UniqueId> implements Serializable {
    @Column(name = "ID")
    @NotBlank
    private final String value;

    public UniqueId() {
        super(UniqueId.class);
        this.value = UUID.randomUUID().toString();
        validate(this);
    }

    public UniqueId(@NotBlank String value) {
        super(UniqueId.class);
        this.value = value;
        validate(this);
    }

    @Override
    protected List<Object> attributesToIncludeInEqualityCheck() {
        return asList(value);
    }

    public String getValue() {
        return value;
    }

    public static class Serializer extends JsonSerializer<UniqueId> {
        @Override
        public void serialize(
                UniqueId id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
        ) throws IOException {
            jsonGenerator.writeString(id.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<UniqueId> {
        @Override
        public UniqueId deserialize(
                JsonParser jsonParser, DeserializationContext deserializationContext
        ) throws IOException {
            return new UniqueId(jsonParser.getText());
        }
    }
}
