package service;

import reader.UserField;
import domain.User;
import reader.ApplicationReader;
import reader.Reader;

import java.io.BufferedReader;
import java.util.HashMap;

public class UserService implements Service {
    private int count;
    private HashMap<Integer, User> users;
    private Reader userReader;
    private BufferedReader reader;

    public UserService(Reader userReader, BufferedReader reader) {
        this.users = new HashMap<>();
        this.count = 0;
        this.userReader = userReader;
        this.reader = reader;
    }

    public UserService(HashMap<Integer, User> users, Reader userReader, BufferedReader reader) {
        this.users = users;
        this.count = users.size();
        this.userReader = userReader;
        this.reader = reader;
    }

    @Override
    public HashMap<Integer, User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(int id) {
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(id);
        while (user == null) {
            System.out.println("User id=" + id + " not found, please try again");
            user = users.get(ApplicationReader.getInt(reader));
        }
        return user;
    }

    @Override
    public User add() {
        User user = userReader.createUser();
        if (users.containsValue(user)) {
            return null;
        }
        users.put(++count, user);
        return user;
    }

    @Override
    public User update(int id) {
        User userById = getUserById(id);
        User user = userReader.createUser();
        if (userById != null) {
            users.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    public User edit(int id) {
        User userById = getUserById(id);
        

        return null;
    }

    @Override
    public boolean delete(int id) {
        if (getUserById(id) == null) {
            return false;
        } else {
            users.remove(id);
            return true;
        }
    }

    @Override
    public Reader getUserReader() {
        return userReader;
    }
}
