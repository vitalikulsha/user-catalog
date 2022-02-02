package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class ApplicationReader {

    public static Integer getInt(BufferedReader reader) {
        while (true) {
            System.out.print("Enter user id: ");
            String id = null;
            try {
                id = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isInteger(id)) {
                return Integer.parseInt(id);
            } else {
                System.out.println("Incorrect id: " + id);
            }
        }
    }

    public static <T extends Enum<T>> T getEnum(BufferedReader reader, Class<T> c) {
        System.out.println(Arrays.toString(c.getEnumConstants()));
        while (true) {
            System.out.print("Select a command from the list: ");
            String stringCommand;
            try {
                stringCommand = reader.readLine().trim().toUpperCase();
            } catch (IOException ex) {
                return null;
            }
            T command = getEnum(stringCommand, c);
            if (command != null) {
                return command;
            } else {
                System.out.println("Incorrect command: " + stringCommand);
            }
        }
    }

    private static boolean isInteger(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static <T extends Enum<T>> T getEnum(String command, Class<T> c) {
        if (c != null && command != null) {
            return Enum.valueOf(c, command);
        }
        return null;
    }
}
