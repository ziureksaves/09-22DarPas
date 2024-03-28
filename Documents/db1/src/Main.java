import java.util.Scanner;

import static auth.Auth.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sveiki atvyke į maitinimo įstaigų gidą");

        int selectedOption;
        while (true) {
            System.out.println("Įveskite 1 jei prisijungti, 2 jei prisiregistruoti: ");
            selectedOption = scanner.nextInt();
            if (selectedOption == 1 || selectedOption == 2)
                break;
        }

        if (selectedOption == 1) {
            showLoginWindow();
        } else {
            showRegisterWindow();
        }

    }
}