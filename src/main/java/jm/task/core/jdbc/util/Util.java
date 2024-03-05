package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    // реализуйте настройку соеденения с БД
    public static SessionFactory getSessionFactory () {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        Map<String,String> settings = new HashMap<>();
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/user_db");
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "1234");
        standardServiceRegistryBuilder.applySettings(settings);
        StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry).addAnnotatedClass(User.class);
        SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/user", "user", "user");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.exit(1);
        }
        return con;
    }

    public static Connection connectMysql() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user_db", "root", "1234");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.exit(1);
        }
        return con;
    }
}
