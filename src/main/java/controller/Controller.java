package controller;

import java.io.BufferedReader;

public interface Controller {
    void printAllUsers();

    void printUserById(Integer id);

    void addUser();

    void updateUser(Integer id);

    void editUser(Integer id);

    void deleteUser(Integer id);

    void stopReader();

    BufferedReader getReader();
}
