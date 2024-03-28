package utils;

import java.sql.*;

public class DatabaseHelper {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/uzsakymai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}