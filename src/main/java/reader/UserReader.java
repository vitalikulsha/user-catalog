package reader;

import domain.Role;
import domain.User;
import validator.EmailValidator;
import validator.PhoneNumberValidator;
import validator.RoleValidator;
import validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserReader implements Reader {
    private final Validator<Role> roleValidator;
    private final Validator<Long> phoneNumberValidator;
    private final Validator<String> emailValidator;
    private final BufferedReader reader;

    public UserReader() {
        this.roleValidator = new RoleValidator();
        this.phoneNumberValidator = new PhoneNumberValidator();
        this.emailValidator = new EmailValidator();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public UserReader(Validator<Role> roleValidator, Validator<Long> phoneNumberValidator,
                      Validator<String> emailValidator, BufferedReader reader) {
        this.roleValidator = roleValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.emailValidator = emailValidator;
        this.reader = reader;
    }

    @Override
    public User createUser() {
        try {
            String firstName = enterFirstName();
            String lastName = enterLastName();
            String email = enterEmail();
            List<Role> roles = enterRoles();
            List<Long> phoneNumbers = enterPhoneNumbers();
            return new User(firstName, lastName, email, roles, phoneNumbers);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the user:" + e.getMessage());
            return null;
        }
    }

    @Override
    public void stop() {
        try {
            reader.close();
            System.out.println("Application shutdown.");
        } catch (IOException e) {
            System.out.println("An error occurred while stopping the application: " + e.getMessage());
        }
    }

    @Override
    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public String enterFirstName() throws IOException {
        System.out.print("Enter the user's first name: ");
        return reader.readLine();
    }

    @Override
    public String enterLastName() throws IOException {
        System.out.print("Enter the user's last name: ");
        return reader.readLine();
    }

    @Override
    public String enterEmail() throws IOException {
        return emailValidator.getValue(reader, null);
    }

    @Override
    public List<Role> enterRoles() throws IOException {
        System.out.println(Arrays.toString(Role.values()));
        List<Role> roles = new ArrayList<>();
        String flag = "Y";
        while (flag.equalsIgnoreCase("Y")) {
            Role role = roleValidator.getValue(reader, roles.size() + 1);
            if (roleValidator.verifyAndRepeat(roles, role)) {
                if (roleValidator.verifyAndBreak(roles, role)) {
                    roles.add(role);
                } else {
                    break;
                }
            } else {
                continue;
            }
            flag = roleValidator.getFlag(reader, roles);
        }
        return roles;
    }

    @Override
    public List<Long> enterPhoneNumbers() throws IOException {
        List<Long> phoneNumbers = new ArrayList<>();
        String flag = "Y";
        while (flag.equalsIgnoreCase("Y")) {
            Long phoneNumber = phoneNumberValidator.getValue(reader, (phoneNumbers.size() + 1));
            if (phoneNumberValidator.verifyAndRepeat(phoneNumbers, phoneNumber)) {
                phoneNumbers.add(phoneNumber);
            } else {
                continue;
            }
            flag = phoneNumberValidator.getFlag(reader, phoneNumbers);
        }
        return phoneNumbers;
    }
}
