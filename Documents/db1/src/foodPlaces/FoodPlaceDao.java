package foodPlaces;

import utils.DatabaseHelper;

import java.sql.*;

public class FoodPlaceDao {

    public static void printAllPlaces() {

        try {
            Connection connection = DatabaseHelper.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM places");

            int placeCount = 0;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println("# id: " + id + " | " + name + " | " + address);
                placeCount++;
            }

            if (placeCount == 0) {
                System.out.println("> Šiuo nėra maitinimo įstaigų");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }

    }

    public static void findAndPrintPlaces(String nameToFind) {
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM places WHERE name LIKE ?");
            preparedStatement.setString(1, "%" + nameToFind + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            int placeCount = 0;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println("# id: " + id + " | " + name + " | " + address);
                placeCount++;
            }

            if (placeCount == 0) {
                System.out.println("> Nerasta '" + nameToFind + "' maitinimo įstaigų");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }
    }

    public static FoodPlace getSinglePlace(int placeid) {
        FoodPlace selectedPlace = null;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM places WHERE id = ? LIMIT 1");

            preparedStatement.setInt(1, placeid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                selectedPlace = new FoodPlace(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }
        return selectedPlace;
    }

    public static boolean insertNewPlace(String name, String address) {
        boolean success = false;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT IGNORE INTO places (name, address) VALUES (?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);

            int changed = preparedStatement.executeUpdate();

            if (changed > 0) {
                success = true;
                System.out.println("> Nauja maitinimo įstaiga įdėta");
            } else {
                System.out.println("> Nepavyko sukūrti maitinimo įstaigos, gali būti kad įstaiga tokiu pavadinimu jau egzistuoja");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }

        return success;
    }

    public static boolean deletePlace(int placeId) {
        boolean success = false;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM places WHERE id = ? LIMIT 1");

            preparedStatement.setInt(1, placeId);

            int changed = preparedStatement.executeUpdate();

            if (changed > 0) {
                success = true;
                System.out.println("> Įstaiga panaikinta");
            } else {
                System.out.println("> Įstaigos nepavyko panaikinti, įsitikinkite, kad id yra tinkamas");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }

        return success;
    }

    public static boolean editPlace(int placeId, String name, String address) {
        boolean success = false;
        try {
            Connection connection = DatabaseHelper.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE places SET name = ?, address = ? WHERE id = ?");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, placeId);

            int changed = preparedStatement.executeUpdate();

            if (changed > 0) {
                success = true;
                System.out.println("> Įstaiga pakeista");
            } else {
                System.out.println("> Įstaigos nepavyko pakeisti, įsitikinkite, kad įstaiga egzistuoja");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException err) {
            System.out.println("Duomenų bazės klaida");
            System.out.println(err);
        }

        return success;
    }
}
