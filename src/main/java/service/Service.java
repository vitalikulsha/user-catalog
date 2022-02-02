package service;

import domain.User;
import reader.Reader;

import java.util.HashMap;

public interface Service {
    HashMap<Integer, User> getAllUsers();

    User getUserById(Integer id);

    User add();

    User update(Integer id);

    User edit(Integer id);

    boolean delete(Integer id);

    Reader getUserReader();
}
