package validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmailValidator implements Validator<String> {

    private final static String FORMAT = "****@***.***";
    private final static String PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    @Override
    public String getValue(BufferedReader reader, Integer index) throws IOException {
        while (true) {
            System.out.print("Enter the user's email in the format " + FORMAT + ": ");
            String email = reader.readLine();
            if (email.matches(PATTERN)) {
                return email;
            } else {
                System.out.println("Incorrect email: " + email);
            }
        }
    }

    @Override
    public String getFlag(BufferedReader reader, List<String> objects) throws IOException {
        return null;
    }

    @Override
    public boolean verifyAndBreak(List<String> objects, String object) {
        return false;
    }

    @Override
    public boolean verifyAndRepeat(List<String> objects, String object) {
        return false;
    }
}
