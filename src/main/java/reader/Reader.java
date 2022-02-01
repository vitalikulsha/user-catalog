package reader;

import domain.Role;
import domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Reader {
    User createUser();

    void stop();

    BufferedReader getReader();

    String enterFirstName() throws IOException;

    String enterLastName() throws IOException;

    String enterEmail() throws IOException;

    List<Role> enterRoles() throws IOException;

    List<Long> enterPhoneNumbers() throws IOException;
}
