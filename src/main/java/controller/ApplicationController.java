package controller;

import util.reader.ApplicationReader;
import util.reader.Command;

import java.io.BufferedReader;
import java.util.Objects;

public class ApplicationController {
    private final Controller controller;

    public ApplicationController(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        BufferedReader reader = controller.getReader();
        Command command = ApplicationReader.getEnum(reader, Command.class);
        switch (Objects.requireNonNull(command)) {
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
