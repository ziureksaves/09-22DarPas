package auth;

import user.User;
import utils.DatabaseHelper;
import utils.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    public static boolean checkIfUserExists(String username) {
        boolean userExists = false;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                userExists = true;

            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }
        return userExists;
    }

    public static boolean registerUser(String username, String password) {
        boolean registerSuccess = false;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)");

            String hashedPassword = Password.hashPassword(password);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, "vartotojas");

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Sėkmingai užregistruota");
                registerSuccess = true;
            } else {
                System.out.println("Registracija nepavyko");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }
        return registerSuccess;
    }

    public static User loginUser(String username, String password) {
        User loggedInUser = null;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1");

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String passwordHash = resultSet.getString("password_hash");
                String userRole = resultSet.getString("role");

                if (Password.checkPassword(password, passwordHash)) {
                    loggedInUser = new User(userId, username, userRole);
                }
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }

        return loggedInUser;
    }
}
