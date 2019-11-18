package com.luxoft.probability.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtils {
    public static BufferedReader bufferedReader(String path) {
        final InputStream resource = FileUtils.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) {
            throw new IllegalArgumentException("Missing resource: " + path);
        }

        return new BufferedReader(new InputStreamReader(resource, UTF_8));
    }

    public static String loadFile(String path) {
        try (BufferedReader reader = bufferedReader(path)) {
            final StringBuilder result = new StringBuilder();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                result.append(line).append('\n');
            }

            return result.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}