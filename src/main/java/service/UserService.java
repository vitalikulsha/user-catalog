package service;

import domain.User;
import reader.ApplicationReader;
import reader.Reader;
import reader.UserField;
import util.Answer;

import java.io.BufferedReader;
import java.io.IOException;
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
    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public User add() {
        User user = userReader.enterUser();
        if (users.containsValue(user)) return null;
        users.put(++count, user);
        return user;
    }

    @Override
    public User update(Integer id) {
        User currentUser = getUserById(id);
        if (currentUser == null) return null;
        System.out.println("id=" + id + ": " + currentUser);
        while (true) {
            User newUser = userReader.enterUser();
            if (users.containsValue(newUser)) {
                System.out.println(Answer.EXISTS + " Try again.");
            } else {
                users.put(id, newUser);
                return newUser;
            }
        }
    }

    @Override
    public User edit(Integer id) {
        User user = getUserById(id);
        if (user == null) return null;
        System.out.println("id=" + id + ": " + user);
        UserField userField = ApplicationReader.getEnum(reader, UserField.class);
        try {
            switch (userField) {
                case FIRST_NAME -> user.setFirstName(userReader.enterFirstName());
                case LAST_NAME -> user.setLastName(userReader.enterLastName());
                case EMAIL -> user.setEmail(userReader.enterEmail());
                case ROLE -> user.setRoles(userReader.enterRoles());
                case PHONE_NUMBER -> user.setPhoneNumbers(userReader.enterPhoneNumbers());
                case EXIT -> {
                    users.put(id, user);
                    return user;
                }
                default -> System.out.println("Command entry exception");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while editing the user:" + e.getMessage());
            return null;
        }
        edit(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User delete(Integer id) {
        return users.remove(id);
    }

    @Override
    public Reader getUserReader() {
        return userReader;
    }
}
