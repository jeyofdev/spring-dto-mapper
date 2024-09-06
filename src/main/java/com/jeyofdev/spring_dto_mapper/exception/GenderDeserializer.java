package com.jeyofdev.spring_dto_mapper.exception;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jeyofdev.spring_dto_mapper.enums.Gender;

import java.io.IOException;

public class GenderDeserializer extends JsonDeserializer<Gender> {
    @Override
    public Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String value = p.getText().toUpperCase();

        try {
            return Gender.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid gender value: " + value);
        }
    }
}

