package reader;

import domain.User;

import java.io.BufferedReader;

public interface Reader {
    User createUser();

    void stop();

    BufferedReader getReader();
}
