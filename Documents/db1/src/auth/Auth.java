package auth;

import menu.Menu;
import user.User;

import java.util.Scanner;

public class Auth {
    public static void showRegisterWindow() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Įveskite vartotojo vardą pvz: \"vardenis\"");

            String username;
            while (true) {
                username = scanner.nextLine();
                if (username.length() < 3) {
                    System.out.println("Vartotojo vardas per trumpas, turi būti bent 3 simboliai, įveskite iš naujo");
                    continue;
                }
                if (AuthDao.checkIfUserExists(username)) {
                    System.out.println("Vartotojas jau egzistuoja, įveskite kitą vardą");
                    continue;
                }
                break;
            }

            System.out.println("Įveskite vartotojo slaptažodį");
            String password;
            while (true) {
                password = scanner.nextLine();
                if (password.length() < 5) {
                    System.out.println("Vartotojo slaptažodis per trumpas, turi būti bent 5 simboliai, įveskite iš naujo.");
                    continue;
                }
                break;
            }

            boolean registredSuccesfully = AuthDao.registerUser(username, password);
            if (registredSuccesfully)
                break;
        }

        System.out.println("Prašome prisijungti");

        showLoginWindow();
    }

    public static void showLoginWindow() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Įveskite jūsų vartotojo vardą: ");
            String username = scanner.nextLine();

            System.out.println("Įveskite jūsų vartotojo slaptažodį: ");
            String password = scanner.nextLine();

            User loggedInUser = AuthDao.loginUser(username, password);
            if (loggedInUser == null) {
                System.out.println("Netinka vartotojo vardas arba slaptažodis, bandykite prisijungti iš naujo");
                continue;
            }

            Menu.showMenu(loggedInUser);

            break;
        }
    }
}
