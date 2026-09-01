package sentinel.service;

import sentinel.model.User;

public class LoginService {

    private final UserService userService;

    public LoginService() {
        userService = new UserService();
    }

    public User login(String username, String password) {

        User user = userService.login(
                username,
                password
        );

        if (user == null) {

            System.out.println(
                    "Invalid username or password."
            );

            return null;
        }

        System.out.println(
                "Login successful. Welcome "
                        + user.getUsername()
        );

        System.out.println(
                "Role: " + user.getRole()
        );

        return user;
    }

    public boolean isAdmin(User user) {

        return user != null
                && "ADMIN".equalsIgnoreCase(
                user.getRole()
        );
    }

    public boolean isUser(User user) {

        return user != null
                && "USER".equalsIgnoreCase(
                user.getRole()
        );
    }
}