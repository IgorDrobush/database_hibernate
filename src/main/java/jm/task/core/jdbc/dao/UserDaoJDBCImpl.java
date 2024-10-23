package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, last_name VARCHAR(45) NOT NULL, age INT NOT NULL)";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String query = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";

        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }
    }

    public void removeUserById(long id) {

        String query = "DELETE FROM users WHERE id = ?";

        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM users";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка установки соединения: " + e);
        }
    }
}
