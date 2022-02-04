package util.validator;

import util.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmailValidator implements Validator<String> {

    private final static String EMAIL_PATTERN = "emailPattern.regexp";

    @Override
    public String getValue(BufferedReader reader, Integer index) throws IOException {
        String emailPattern = Property.getValue(EMAIL_PATTERN);
        while (true) {
            System.out.print("Enter the user's email in the format ****@***.***: ");
            String email = reader.readLine();
            if (email.matches(emailPattern)) {
                return email;
            } else {
                System.out.println("Incorrect email: " + email);
            }
        }
    }

    @Override
    public String getFlag(BufferedReader reader, List<String> objects) {
        return null;
    }

    @Override
    public boolean verifyAndBreak(List<String> objects, String object) {
        return false;
    }

    @Override
    public boolean verifyAndContinue(List<String> objects, String object) {
        return false;
    }
}
