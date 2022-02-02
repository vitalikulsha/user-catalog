package service;

import domain.User;
import reader.ApplicationReader;
import reader.Reader;
import reader.UserField;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    //сделать как метод delete
    @Override
    public User update(Integer id) {
        if (users.isEmpty()) {
            return null;
        }
        User currentUser = getUserById(id);
        id = getId(currentUser);
        System.out.println("id=" + id + ": " + currentUser);
        User user = userReader.createUser();
        if (currentUser != null) {
            users.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    //сделать как метод delete
    @Override
    public User edit(Integer id) {
        if (users.isEmpty()) {
            return null;
        }
        User user = getUserById(id);
        id = getId(user);
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
    public boolean delete(Integer id) {
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

    private Integer getId(User user) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (user.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
