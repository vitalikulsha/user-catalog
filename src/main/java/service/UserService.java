package service;

import domain.User;
import util.reader.ApplicationReader;
import util.reader.Reader;
import util.reader.UserField;
import util.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserService implements Service {
    private int count;
    private final HashMap<Integer, User> users;
    private final Reader userReader;
    private final BufferedReader reader;

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
            switch (Objects.requireNonNull(userField)) {
                case FIRST_NAME -> user.setFirstName(userReader.enterFirstName());
                case LAST_NAME -> user.setLastName(userReader.enterLastName());
                case EMAIL -> user.setEmail(userReader.enterEmail());
                case ROLE -> user.setRoles(userReader.enterRoles());
                case PHONE -> user.setPhoneNumbers(userReader.enterPhoneNumbers());
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
    public Integer getId(User user) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (entry.getValue().equals(user)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Reader getUserReader() {
        return userReader;
    }
}
