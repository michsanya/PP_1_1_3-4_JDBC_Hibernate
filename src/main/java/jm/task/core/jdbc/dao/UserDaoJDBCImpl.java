package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.connectMysql();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Users(" +
                    "id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(30)," +
                    "lastname VARCHAR(30)," +
                    "age INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE Users");
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Users (name, lastname, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + " );");
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM Users WHERE ID = " + id + ";");
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while (resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE Users ;");
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}