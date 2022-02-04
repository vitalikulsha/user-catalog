package util.reader;

import domain.Role;
import domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Reader {
    User enterUser();

    String enterFirstName() throws IOException;

    String enterLastName() throws IOException;

    String enterEmail() throws IOException;

    List<Role> enterRoles() throws IOException;

    List<Long> enterPhoneNumbers() throws IOException;

    void stop();

    BufferedReader getReader();
}
