package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
            UserService userservice = new UserServiceImpl();
            userservice.createUsersTable();
            userservice.saveUser("Alis", "Vesad", (byte) 23);
            userservice.saveUser("Boris", "Nesew", (byte) 26);
            userservice.saveUser("Celine", "Loi", (byte) 12);
            userservice.saveUser("Domina", "Mif", (byte) 78);
            List<User> users = userservice.getAllUsers();
            System.out.println(users);
    }
}
