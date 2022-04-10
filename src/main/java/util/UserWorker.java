package util;

import domain.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserWorker {
    private final static String FILE_PATH = "%s/#%d%s";
    private final static String DIR = "dir";
    private final static String TYPE = "type";

    public static void writeUserToFile(Integer id, User user) {
        String dir = Property.getValue(DIR);
        String type = Property.getValue(TYPE);
        String filePath = String.format(FILE_PATH, dir, id, type);
        try {
            assert dir != null;
            if (!Files.isDirectory(Path.of(dir))) {
                Files.createDirectories(Path.of(dir));
            }
            Files.writeString(Path.of(filePath), user.toString(), StandardCharsets.UTF_8);
            System.out.println("File was successfully written: " + Path.of(filePath).toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to write file: " + e);
        }
    }

    public static void deleteUserFile(Integer id) {
        String dir = Property.getValue(DIR);
        String type = Property.getValue(TYPE);
        String filePath = String.format(FILE_PATH, dir, id, type);
        try {
            Files.delete(Path.of(filePath));
            System.out.println("File deleted successfully: " + Path.of(filePath).toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
