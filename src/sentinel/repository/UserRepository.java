package sentinel.repository;

import sentinel.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    // Save / Register User
    public boolean save(User user) {

        String sql = """
                INSERT INTO users
                (username, email, password, role)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());

            statement.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error saving user: " + e.getMessage()
            );

            return false;
        }
    }

    // Find User by Username
    public User findByUsername(String username) {

        String sql =
                "SELECT * FROM users WHERE username = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error finding user: " + e.getMessage()
            );
        }

        return null;
    }

    // Check Username Exists
    public boolean existsByUsername(String username) {

        String sql =
                "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1) > 0;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error checking username: "
                            + e.getMessage()
            );
        }

        return false;
    }
}