package validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PhoneNumberValidator implements Validator<Long> {

    private final static String FORMAT = "375*****";
    private final static String PATTERN = "375\\d{9}";
    private final static int MAX_AMOUNT = 3;

    @Override
    public Long getValue(BufferedReader reader, Integer index) throws IOException {
        while (true) {
            System.out.print("The user can have from 1 to 3 phones. Enter #" + index +
                    " user phone number in the format " + FORMAT + ": ");
            String phoneNumber = reader.readLine();
            if (phoneNumber.matches(PATTERN)) {
                return Long.parseLong(phoneNumber);
            } else {
                System.out.println("Incorrect phone number: " + phoneNumber);
            }
        }
    }

    @Override
    public String getFlag(BufferedReader reader, List<Long> phoneNumbers) throws IOException {
        if (phoneNumbers.size() != MAX_AMOUNT) {
            System.out.print("Add another phone number?(Y/N): ");
            return reader.readLine();
        } else {
            System.out.println("You have entered the maximum number of phone numbers allowed");
            return "N";
        }
    }

    @Override
    public boolean verifyAndBreak(List<Long> objects, Long object) {
        return false;
    }

    @Override
    public boolean verifyAndRepeat(List<Long> phoneNumbers, Long phoneNumber) {
        if (phoneNumbers.contains(phoneNumber)) {
            System.out.println("The user already has the " + phoneNumber + " phone number selected.");
            return false;
        } else {
            return true;
        }
    }
}
