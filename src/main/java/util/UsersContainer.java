package util;

import domain.Role;
import domain.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsersContainer {

    private final HashMap<Integer, User> users = new HashMap<>() {{
        put(1, new User("Ivan", "Ivanov", "ivan@gmail.com",
                Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toCollection(ArrayList::new)),
                Stream.of(375123456789L).collect(Collectors.toCollection(ArrayList::new))));
        put(2, new User("Petr", "Petrov", "petr@gmail.com",
                Stream.of(Role.CUSTOMER, Role.PROVIDER).collect(Collectors.toCollection(ArrayList::new)),
                Stream.of(242L, 302L).collect(Collectors.toCollection(ArrayList::new))));
        put(3, new User("Sergey", "Sergeev", "sergey@gmail.com",
                Stream.of(Role.SUPER_ADMIN).collect(Collectors.toCollection(ArrayList::new)),
                Stream.of(100L, 200L).collect(Collectors.toCollection(ArrayList::new))));
    }};

    public HashMap<Integer, User> getUsers() {
        return users;
    }
}



