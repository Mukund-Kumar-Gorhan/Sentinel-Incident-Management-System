package sentinel;

import sentinel.model.User;
import sentinel.service.LoginService;

public class LoginTest {

    public static void main(String[] args) {

        LoginService loginService =
                new LoginService();


        // ==============================
        // USER LOGIN
        // ==============================

        System.out.println("=================================");
        System.out.println("          USER LOGIN");
        System.out.println("=================================");

        User user =
                loginService.login(
                        "mukund_user",
                        "mukund123"
                );

        if (loginService.isUser(user)) {

            System.out.println(
                    "Opening USER dashboard..."
            );
        }


        // ==============================
        // ADMIN LOGIN
        // ==============================

        System.out.println("\n=================================");
        System.out.println("         ADMIN LOGIN");
        System.out.println("=================================");

        User admin =
                loginService.login(
                        "admin",
                        "admin123"
                );

        if (loginService.isAdmin(admin)) {

            System.out.println(
                    "Opening ADMIN dashboard..."
            );
        }


        // ==============================
        // INVALID LOGIN
        // ==============================

        System.out.println("\n=================================");
        System.out.println("        INVALID LOGIN");
        System.out.println("=================================");

        User invalid =
                loginService.login(
                        "admin",
                        "wrongpassword"
                );

        if (invalid == null) {

            System.out.println(
                    "Access denied successfully."
            );
        }
    }
}