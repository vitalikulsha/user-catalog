package controller;

import java.io.BufferedReader;

public interface Controller {
    void printAllUsers();

    void printUserById(int id);

    void addUser();

    void updateUser(int id);

    void editUser(int id);

    void deleteUser(int id);

    void stopReader();

    BufferedReader getReader();
}
