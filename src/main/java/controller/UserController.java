package controller;

import domain.User;
import service.Service;


import java.io.BufferedReader;
import java.util.Map;

public class UserController implements Controller {
    private Service service;

    public UserController(Service service) {
        this.service = service;
    }

    @Override
    public void printAllUsers() {
        Map<Integer, User> users = service.getAllUsers();
        if (!users.isEmpty()) {
            for (Map.Entry<Integer, User> entry : service.getAllUsers().entrySet()) {
                System.out.println("id=" + entry.getKey() + ": " + entry.getValue().toString());
            }
        } else {
            System.out.println("User list is empty.");
        }
    }

    @Override
    public void printUserById(Integer id) {
        User user = service.getUserById(id);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User list is empty.");
        }
    }

    @Override
    public void addUser() {
        User user = service.add();
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Such user already exist.");
        }
    }

    @Override
    public void updateUser(Integer id) {
        User user = service.update(id);
        if (user != null) {
            System.out.println("Updated user: " + user);
        } else {
            System.out.println("User with this id was not found");
        }
    }

    @Override
    public void editUser(Integer id) {
        User user = service.edit(id);
        if (user != null) {
            System.out.println("Edited user: " + user);
        } else {
            System.out.println("User with this id was not found");
        }
    }

    @Override
    public void deleteUser(Integer id) {
        if (service.delete(id)) {
            System.out.println("User id=" + id + " deleted successfully");
        } else {
            System.out.println("User id=" + id + " not found");
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
}
