package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User user1 = new User("Андрей", "Иваныч", (byte) 34);
        User user2 = new User("Сергей", "Васильевич", (byte) 25);
        User user3 = new User("Игорь", "Владимирович", (byte) 68);
        User user4 = new User("Евгений", "Викторович", (byte) 51);
        List<User> users = List.of(user1, user2, user3, user4);

        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем — " + user.getName() + " добавлен в базу данных");
        }

        List<User> usersList = userService.getAllUsers();
        for (User user : usersList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
