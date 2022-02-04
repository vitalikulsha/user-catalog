package service;

import domain.User;
import util.reader.Reader;

import java.util.HashMap;

public interface Service {
    HashMap<Integer, User> getAllUsers();

    User getUserById(Integer id);

    User add();

    User update(Integer id);

    User edit(Integer id);

    User delete(Integer id);

    Integer getId(User user);

    Reader getUserReader();
}
