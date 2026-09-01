package sentinel;

import sentinel.model.User;
import sentinel.service.LoginService;

public class RoleTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       SENTINEL ROLE TEST");
        System.out.println("=================================");

        LoginService loginService = new LoginService();

        // ==============================
        // USER LOGIN
        // ==============================

        System.out.println("\nTesting USER login:");

        User user = loginService.login(
                "mukund",
                "123456"
        );

        if (user != null) {

            System.out.println("Login successful.");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());

            if ("USER".equals(user.getRole())) {
                System.out.println("USER access granted.");
            } else {
                System.out.println("USER access denied.");
            }

        } else {

            System.out.println("Login failed.");
        }


        // ==============================
        // ROLE INFORMATION
        // ==============================

        System.out.println("\n=================================");
        System.out.println("       ROLE SYSTEM");
        System.out.println("=================================");

        System.out.println("USER  -> View and search incidents");
        System.out.println("ADMIN -> Manage incidents");
        System.out.println("ADMIN -> Add / Delete incidents");
        System.out.println("ADMIN -> Change incident status");

        System.out.println("\n=================================");
        System.out.println("       ROLE TEST COMPLETE");
        System.out.println("=================================");
    }
}