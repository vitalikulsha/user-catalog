package util.validator;

import util.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PhoneNumberValidator implements Validator<Long> {

    private final static String FORMAT = "phoneFormat";
    private final static String PATTERN = "phonePattern.regexp";
    private final static String MAX_AMOUNT = "phoneMaxAmount";

    @Override
    public Long getValue(BufferedReader reader, Integer index) throws IOException {
        String format = Property.getValue(FORMAT);
        String pattern = Property.getValue(PATTERN);
        while (true) {
            System.out.print("The user can have from 1 to 3 phones. Enter #" + index +
                    " user phone number in the format " + format + ": ");
            String phoneNumber = reader.readLine();
            if (phoneNumber.matches(pattern)) {
                return Long.parseLong(phoneNumber);
            } else {
                System.out.println("Incorrect phone number: " + phoneNumber);
            }
        }
    }

    @Override
    public String getFlag(BufferedReader reader, List<Long> phoneNumbers) throws IOException {
        int maxAmount = Integer.parseInt(Property.getValue(MAX_AMOUNT));
        if (phoneNumbers.size() != maxAmount) {
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
    public boolean verifyAndContinue(List<Long> phoneNumbers, Long phoneNumber) {
        if (phoneNumbers.contains(phoneNumber)) {
            System.out.println("The user already has the " + phoneNumber + " phone number selected.");
            return false;
        } else {
            return true;
        }
    }
}
