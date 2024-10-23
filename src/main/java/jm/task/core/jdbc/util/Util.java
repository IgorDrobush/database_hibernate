package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/study_database";
        String username = "root";
        String password = "9213888744";

        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println("Соединение установлено");
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            String url = "jdbc:mysql://127.0.0.1:3306/study_database";
//            String username = "root";
//            String password = "9213888744";
//
//            connection = DriverManager.getConnection(url, username, password);
//
//            System.out.println("Соединение установлено");
//        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println("Ошибка установки соединения");
//        }

        return connection;
    }
}
