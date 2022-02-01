import controller.ApplicationController;
import controller.Controller;
import controller.UserController;
import domain.User;
import service.Service;
import service.UserService;
import reader.Reader;
import reader.UserReader;
import util.UsersContainer;
import validator.EmailValidator;
import validator.PhoneNumberValidator;
import validator.RoleValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UsersContainer container = new UsersContainer();
        HashMap<Integer, User> users = container.getUsers();

        Reader userReader = new UserReader(new RoleValidator(), new PhoneNumberValidator(), new EmailValidator(), reader);
        Service userService = new UserService(users, userReader, reader);
        Controller userController = new UserController(userService);

        ApplicationController applicationController = new ApplicationController(userController);
        applicationController.start();


    }
}
