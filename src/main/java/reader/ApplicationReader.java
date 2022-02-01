package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class ApplicationReader<Enum> {

    public static int getInt(BufferedReader reader) {
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

    private static boolean isInteger(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Command getCommand(BufferedReader reader) {
        Arrays.stream(Command.values()).forEach(System.out::println);
        while (true) {
            System.out.print("Select a command from the list: ");
            String command = null;
            try {
                command = reader.readLine().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isCommand(command)) {
                return Command.valueOf(command);
            } else {
                System.out.println("Incorrect command: " + command);
            }
        }
    }

    private static boolean isCommand(String command) {
        for (Command c : Command.values()) {
            if (c.name().equalsIgnoreCase(command)) {
                return true;
            }
        }
        return false;
    }

    public static UserField getUserField(BufferedReader reader) {
        Arrays.stream(UserField.values()).forEach(System.out::println);
        while (true) {
            System.out.print("Select a user field from the list: ");
            String userField = null;
            try {
                userField = reader.readLine().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isUserField(userField)) {
                return UserField.valueOf(userField);
            } else {
                System.out.println("Incorrect user field: " + userField);
            }
        }
    }

    private static boolean isUserField(String userField) {
        for (UserField uf : UserField.values()) {
            if (uf.name().equalsIgnoreCase(userField)) {
                return true;
            }
        }
        return false;
    }
}
