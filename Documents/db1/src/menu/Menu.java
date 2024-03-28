package menu;

import foodPlaces.FoodPlace;
import foodPlaces.FoodPlaceDao;
import user.User;

import java.util.*;

public class Menu {
    public static void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //normal user and admin
            System.out.println("----");
            System.out.println("Pasirinkite: ");
            System.out.println("1 - visos įstaigų sąrašas");
            System.out.println("2 - pasirinkti įstaiga");
            System.out.println("3 - įstaigos paieška");
//            System.out.println("4 - mano užsakymai");
            System.out.println("6 - atsijungti");

            //admin only
            if (user.isAdmin()) {
                System.out.println("7 - pridėti įstaigą");

            }

            int userSelected = scanner.nextInt();

            //normal user and admin
            if (userSelected == 1) {
                showAllPlacesWindow();
            } else if (userSelected == 2) {
                showSelectedPlaceWindow(user);
            } else if (userSelected == 3) {
                showFindPlaceWindow();
            } else if (userSelected == 4) {
            } else if (userSelected == 5) {
            } else if (userSelected == 6) {
                System.out.println("> Geros dienos!");
                break;

                //admin only
            } else if (userSelected == 7 && user.isAdmin()) {
                showAddNewPlaceWindow(user);
            } else if (userSelected == 8 && user.isAdmin()) {
            }
        }
    }

    public static void showAllPlacesWindow() {
        System.out.println("Visos maitinimo įstaigos:");
        FoodPlaceDao.printAllPlaces();
    }

    public static void showFindPlaceWindow() {
        System.out.println("Maitinimo įstaigos paieška:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Įveskite maitinimo pavadinimą: ");
        String nameToFind = scanner.nextLine();
        FoodPlaceDao.findAndPrintPlaces(nameToFind);
    }

    public static void showSelectedPlaceWindow(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Įveskite maitinimo įstaigos id: ");
        int placeId = scanner.nextInt();
        scanner.nextLine(); //scanner fix

        FoodPlace place = FoodPlaceDao.getSinglePlace(placeId);

        if (place == null) {
            System.out.println("> Tokia įstaiga neegzistuoja");
        } else {
            place.printInfo();

            ArrayList<String> options = new ArrayList<>();
            options.add("grizti");

            if(user.isAdmin()){
                options.add("redaguoti");
                options.add("pasalinti");
            }

            System.out.println("Pasirinkite: " + String.join(", ", options));

            String userSelected = scanner.nextLine();
            if(userSelected.equals("redaguoti") && user.isAdmin()){

            }else if(userSelected.equals("pasalinti") && user.isAdmin()){
                System.out.println("Šalinama maitinimo įstaiga:");
                FoodPlaceDao.deletePlace(placeId);
            }
        }
    }

    public static void showAddNewPlaceWindow(User user) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String address;
        while (true) {
            System.out.println("Įveskite naujos maitinimo įstaigos pavadinimą: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Pavadinimas negali būti tučias");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Įveskite naujos maitinimo įstaigos adresą: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Adresas negali būti tučias");
            } else {
                break;
            }
        }

        FoodPlaceDao.insertNewPlace(name, address);

    }

}