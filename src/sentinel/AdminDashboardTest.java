package sentinel;

import sentinel.model.User;

public class AdminDashboardTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     ADMIN DASHBOARD TEST");
        System.out.println("=================================");

        User admin = new User(
                1,
                "admin",
                "admin@sentinel.com",
                "admin123",
                "ADMIN"
        );

        AdminDashboard dashboard =
                new AdminDashboard(admin);

        dashboard.showMenu();

        System.out.println("\n=================================");
        System.out.println("     DASHBOARD TEST COMPLETE");
        System.out.println("=================================");
    }
}