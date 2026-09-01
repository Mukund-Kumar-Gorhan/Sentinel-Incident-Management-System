package sentinel.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IncidentHistoryRepository {

    public void saveHistory(
            int incidentId,
            String oldStatus,
            String newStatus) {

        String sql = """
                INSERT INTO incident_history
                (incident_id, old_status, new_status)
                VALUES (?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, incidentId);
            statement.setString(2, oldStatus);
            statement.setString(3, newStatus);

            statement.executeUpdate();

            System.out.println("Incident history saved.");

        } catch (Exception e) {

            System.out.println(
                    "Error saving incident history: "
                            + e.getMessage()
            );
        }
    }

    public void showHistory(int incidentId) {

        String sql = """
                SELECT incident_id, old_status,
                       new_status, changed_at
                FROM incident_history
                WHERE incident_id = ?
                ORDER BY changed_at
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, incidentId);

            ResultSet resultSet =
                    statement.executeQuery();

            System.out.println();
            System.out.println("=================================");
            System.out.println("       INCIDENT HISTORY");
            System.out.println("=================================");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                System.out.println(
                        "Incident ID : "
                                + resultSet.getInt("incident_id")
                );

                System.out.println(
                        "Old Status  : "
                                + resultSet.getString("old_status")
                );

                System.out.println(
                        "New Status  : "
                                + resultSet.getString("new_status")
                );

                System.out.println(
                        "Changed At  : "
                                + resultSet.getTimestamp("changed_at")
                );

                System.out.println("---------------------------------");
            }

            if (!found) {
                System.out.println(
                        "No history found for this incident."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading incident history: "
                            + e.getMessage()
            );
        }
    }
}