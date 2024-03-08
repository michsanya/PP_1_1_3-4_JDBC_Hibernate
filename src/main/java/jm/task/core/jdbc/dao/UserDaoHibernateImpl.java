package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    private  static SessionFactory sessionFactory = getSessionFactory();


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users(" +
                    "id SERIAL PRIMARY KEY ," +
                    "name VARCHAR(30)," +
                    "lastname VARCHAR(30)," +
                    "age INT)").executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable()  {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE Users").executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        User user = new User(name, lastName, age);
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch (Exception  e){
            System.out.println(e);
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        }
        catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            users = (List<User>)session.createQuery("FROM User").getResultList();
        }
        catch (Exception e){
            System.out.println("e = " + e);
            transaction.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            transaction.commit();
        }
        catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }
    }
}
