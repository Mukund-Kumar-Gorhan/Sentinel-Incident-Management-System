package sentinel.service;

import sentinel.model.User;
import sentinel.repository.UserRepository;

public class UserService {

    private final UserRepository repository;

    public UserService() {
        repository = new UserRepository();
    }

    public boolean register(
            String username,
            String email,
            String password) {

        if (username == null ||
                username.isBlank()) {

            System.out.println(
                    "Username cannot be empty."
            );

            return false;
        }

        if (email == null ||
                email.isBlank()) {

            System.out.println(
                    "Email cannot be empty."
            );

            return false;
        }

        if (password == null ||
                password.length() < 6) {

            System.out.println(
                    "Password must contain at least 6 characters."
            );

            return false;
        }

        if (repository.existsByUsername(username)) {

            System.out.println(
                    "Username already exists."
            );

            return false;
        }

        User user = new User(
                username,
                email,
                password,
                "USER"
        );

        return repository.save(user);
    }

    public User login(
            String username,
            String password) {

        User user =
                repository.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}