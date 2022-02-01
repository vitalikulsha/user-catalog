package controller;

import reader.ApplicationReader;
import reader.Command;

import java.io.BufferedReader;

public class ApplicationController {
    private Controller controller;

    public ApplicationController(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        BufferedReader reader = controller.getReader();
        Command command = ApplicationReader.getCommand(reader);
        switch (command) {
            case SHOW_ALL -> controller.printAllUsers();
            case SHOW_USER -> controller.printUserById(ApplicationReader.getInt(reader));
            case CREATE -> controller.addUser();
            case UPDATE -> controller.updateUser(ApplicationReader.getInt(reader));
//            case EDIT -> controller.editUser(ApplicationReader.getInt(reader), userReader.createUser());
            case DELETE -> controller.deleteUser(ApplicationReader.getInt(reader));
            case STOP -> {
                controller.stopReader();
                return;
            }
        }
        start();
    }
}
