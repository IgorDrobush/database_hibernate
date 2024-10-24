package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = Util.getSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Ошибка создания SessionFactory: " + e);
        }
    }

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, last_name VARCHAR(45) NOT NULL, age INT NOT NULL)";
            session.createNativeQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "DROP TABLE IF EXISTS users";
            session.createNativeQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hibernateQuery = "FROM User";
            Query<User> query = session.createQuery(hibernateQuery, User.class);
            userList = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hibernateQuery = "DELETE FROM User";
            session.createQuery(hibernateQuery).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка HibernateException: " + e);
        }
    }
}
