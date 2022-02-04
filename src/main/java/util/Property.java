package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private final static String PATH_TO_PROPERTY = "src/main/resources/application.properties";

    public static String getValue(String key) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTY)) {
            properties.load(fileInputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("Property file not found: " + PATH_TO_PROPERTY);
            e.printStackTrace();
            return null;
        }
    }
}
