package com.luxoft.probability.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static <T> T readJsonObject(String content, Class<T> clType) {
        try {
            return OBJECT_MAPPER.readValue(content, clType);
        }  catch (IOException e) {
            System.out.println("TEST WARNING: Exception while deserialize to class " + clType
                    + " from json file from text");
            throw new RuntimeException(e);
        }
    }
}
