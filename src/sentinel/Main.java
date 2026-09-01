package sentinel;

import sentinel.model.User;
import sentinel.service.LoginService;
import sentinel.service.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        LoginService loginService = new LoginService();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        SENTINEL SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("---------------------------------");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                // ==============================
                // LOGIN
                // ==============================

                case "1":

                    System.out.println("\n=================================");
                    System.out.println("           LOGIN");
                    System.out.println("=================================");

                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    User user =
                            loginService.login(
                                    username,
                                    password
                            );

                    if (user == null) {
                        break;
                    }

                    // ==============================
                    // ROLE CHECK
                    // ==============================

                    if (loginService.isAdmin(user)) {

                        System.out.println(
                                "\nOpening Admin Dashboard..."
                        );

                        AdminDashboard adminDashboard =
                                new AdminDashboard(user);

                        adminDashboard.showMenu();

                    } else if (loginService.isUser(user)) {

                        System.out.println(
                                "\nOpening User Dashboard..."
                        );

                        UserDashboard userDashboard =
                                new UserDashboard(user);

                        userDashboard.showMenu();

                    } else {

                        System.out.println(
                                "Unknown user role."
                        );
                    }

                    break;


                // ==============================
                // REGISTER
                // ==============================

                case "2":

                    System.out.println("\n=================================");
                    System.out.println("         REGISTRATION");
                    System.out.println("=================================");

                    System.out.print("Username: ");
                    String newUsername =
                            scanner.nextLine();

                    System.out.print("Email: ");
                    String email =
                            scanner.nextLine();

                    System.out.print("Password: ");
                    String newPassword =
                            scanner.nextLine();

                    boolean registered =
                            userService.register(
                                    newUsername,
                                    email,
                                    newPassword
                            );

                    if (registered) {

                        System.out.println(
                                "\nRegistration successful!"
                        );

                    } else {

                        System.out.println(
                                "\nRegistration failed."
                        );
                    }

                    break;


                // ==============================
                // EXIT
                // ==============================

                case "3":

                    System.out.println(
                            "\nThank you for using Sentinel."
                    );

                    System.out.println(
                            "System shutting down..."
                    );

                    scanner.close();

                    return;


                // ==============================
                // INVALID
                // ==============================

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}