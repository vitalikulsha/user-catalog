package controller;

import domain.User;
import service.Service;
import util.Answer;
import util.UserWorker;

import java.io.BufferedReader;
import java.util.Map;

public class UserController implements Controller {
    private final Service service;

    public UserController(Service service) {
        this.service = service;
    }

    @Override
    public void printAllUsers() {
        Map<Integer, User> users = service.getAllUsers();
        if (!users.isEmpty()) {
            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                System.out.println("id=" + entry.getKey() + ": " + entry.getValue().toString());
            }
        } else {
            System.out.println(Answer.EMPTY);
        }
    }

    @Override
    public void printUserById(Integer id) {
        if (isEmptyUsers()) return;
        User user = service.getUserById(id);
        if (user != null) {
            System.out.println("id=" + id + ": " + user);
        } else {
            System.out.println(Answer.NOT_FOUND);
        }
    }

    @Override
    public void addUser() {
        User user = service.add();
        if (user != null) {
            Integer id = service.getId(user);
            System.out.println("Created user: id=" + id + ": " + user);
            UserWorker.writeUserToFile(id, user);
        } else {
            System.out.println(Answer.EXISTS);
        }
    }

    @Override
    public void updateUser(Integer id) {
        if (isEmptyUsers()) return;
        User user = service.update(id);
        if (user != null) {
            System.out.println("Updated user: " + user);
            UserWorker.writeUserToFile(id, user);
        } else {
            System.out.println(Answer.NOT_FOUND);
        }
    }

    @Override
    public void editUser(Integer id) {
        if (isEmptyUsers()) return;
        User user = service.edit(id);
        if (user != null) {
            System.out.println("Edited user: " + user);
            UserWorker.writeUserToFile(id, user);
        } else {
            System.out.println(Answer.NOT_FOUND);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        if (isEmptyUsers()) return;
        User user = service.delete(id);
        if (user != null) {
            System.out.println("Deleted user: " + user);
            UserWorker.deleteUserFile(id);
        } else {
            System.out.println(Answer.NOT_FOUND);
        }
    }

    @Override
    public void stopReader() {
        service.getUserReader().stop();
    }

    @Override
    public BufferedReader getReader() {
        return service.getUserReader().getReader();
    }

    private boolean isEmptyUsers() {
        if (service.getAllUsers().isEmpty()) {
            System.out.println(Answer.EMPTY);
            return true;
        }
        return false;
    }
}
