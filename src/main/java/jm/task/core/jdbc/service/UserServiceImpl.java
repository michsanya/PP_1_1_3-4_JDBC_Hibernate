package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connect;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserServiceImpl implements UserService {
    //private final UserDao userDAO = new UserDaoJDBCImpl();
    private final UserDao userDAO = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
            userDAO.createUsersTable();
    }
    public void dropUsersTable() throws SQLException {
             userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
              try {
                  userDAO.saveUser(name, lastName, age);
                  System.out.println("User с именем "+ name+" добавлен в базу данных");
              }
              catch (SQLException e){
                  System.out.println("Пользователь "+ name+" не обавлен");
              }
    }

    public void removeUserById(long id) throws SQLException {
        userDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void cleanUsersTable() {
          userDAO.cleanUsersTable();
    }
}
