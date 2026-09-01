package sentinel.repository;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println("=================================");
            System.out.println("   DATABASE CONNECTION SUCCESS");
            System.out.println("=================================");
            System.out.println("Connected to: sentinel_db");

            connection.close();

        } catch (Exception e) {

            System.out.println("=================================");
            System.out.println("   DATABASE CONNECTION FAILED");
            System.out.println("=================================");
            System.out.println("Error: " + e.getMessage());
        }
    }
}