package com.epam.task.web.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public Properties loadProperties(String filename) throws IOException {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IOException("File doesn't exist");
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }
    }
}
