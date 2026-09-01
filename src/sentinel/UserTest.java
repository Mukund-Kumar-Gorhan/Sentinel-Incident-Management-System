package sentinel;

import sentinel.model.User;
import sentinel.service.UserService;

public class UserTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       SENTINEL USER TEST");
        System.out.println("=================================");

        UserService userService = new UserService();

        System.out.println("\nRegistering User:");

        boolean registered = userService.register(
                "mukund",
                "mukund@gmail.com",
                "123456"
        );

        System.out.println("Registration successful: " + registered);

        System.out.println("\n=================================");
        System.out.println("       TEST COMPLETE");
        System.out.println("=================================");
    }
}