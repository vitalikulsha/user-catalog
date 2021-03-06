import controller.ApplicationController;
import controller.Controller;
import controller.UserController;
import domain.User;
import service.Service;
import service.UserService;
import util.reader.Reader;
import util.reader.UserReader;
import util.UsersContainer;
import util.validator.EmailValidator;
import util.validator.PhoneNumberValidator;
import util.validator.RoleValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Application {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, User> users = new UsersContainer().getUsers();
        Reader userReader = new UserReader(new RoleValidator(), new PhoneNumberValidator(), new EmailValidator(), reader);
        Service userService = new UserService(users, userReader, reader);
        Controller userController = new UserController(userService);
        ApplicationController applicationController = new ApplicationController(userController);
        applicationController.start();
    }
}
