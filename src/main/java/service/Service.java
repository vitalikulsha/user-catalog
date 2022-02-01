package service;

import domain.User;
import reader.Reader;

import java.util.HashMap;

public interface Service {
    HashMap<Integer, User> getAllUsers();

    User getUserById(int id);

    User add();

    User update(int id);

    boolean delete(int id);

    Reader getUserReader();
}
