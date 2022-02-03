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
        Command command = ApplicationReader.getEnum(reader, Command.class);
        switch (command) {
            case SHOW_ALL -> controller.printAllUsers();
            case SHOW_USER -> controller.printUserById(ApplicationReader.getInteger(reader));
            case CREATE -> controller.addUser();
            case UPDATE -> controller.updateUser(ApplicationReader.getInteger(reader));
            case EDIT -> controller.editUser(ApplicationReader.getInteger(reader));
            case DELETE -> controller.deleteUser(ApplicationReader.getInteger(reader));
            case STOP -> {
                controller.stopReader();
                return;
            }
            default -> System.out.println("Command entry exception");
        }
        start();
    }
}
