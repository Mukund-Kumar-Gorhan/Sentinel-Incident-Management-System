package sentinel.repository;

import sentinel.model.Category;
import sentinel.model.Incident;
import sentinel.model.Severity;
import sentinel.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    public void save(Incident incident) {

        String sql = """
                INSERT INTO incidents
                (id, title, description, category, severity, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, incident.getId());
            statement.setString(2, incident.getTitle());
            statement.setString(3, incident.getDescription());
            statement.setString(4, incident.getCategory().name());
            statement.setString(5, incident.getSeverity().name());
            statement.setString(6, incident.getStatus().name());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error saving incident: " + e.getMessage());
        }
    }

    public Incident findById(int id) {

        String sql = "SELECT * FROM incidents WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapIncident(resultSet);
            }

        } catch (Exception e) {
            System.out.println("Error finding incident: " + e.getMessage());
        }

        return null;
    }

    public List<Incident> findAll() {

        List<Incident> incidents = new ArrayList<>();

        String sql = "SELECT * FROM incidents ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                incidents.add(mapIncident(resultSet));
            }

        } catch (Exception e) {
            System.out.println("Error loading incidents: " + e.getMessage());
        }

        return incidents;
    }

    public boolean updateStatus(int id, Status status) {

        String sql =
                "UPDATE incidents SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status.name());
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(
                    "Error updating status: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean deleteById(int id) {

        String sql =
                "DELETE FROM incidents WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(
                    "Error deleting incident: " + e.getMessage()
            );
            return false;
        }
    }

    public int count() {

        String sql =
                "SELECT COUNT(*) FROM incidents";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error counting incidents: " + e.getMessage()
            );
        }

        return 0;
    }

    public boolean existsById(int id) {

        String sql =
                "SELECT COUNT(*) FROM incidents WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (Exception e) {
            System.out.println(
                    "Error checking incident: " + e.getMessage()
            );
        }

        return false;
    }

    /*
     * Display incident status history
     */
    public void showIncidentHistory(int incidentId) {

        String sql = """
                SELECT history_id, incident_id,
                       old_status, new_status, changed_at
                FROM incident_history
                WHERE incident_id = ?
                ORDER BY changed_at
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, incidentId);

            ResultSet resultSet = statement.executeQuery();

            boolean found = false;

            System.out.println("\n=================================");
            System.out.println("       INCIDENT HISTORY");
            System.out.println("=================================");

            while (resultSet.next()) {

                found = true;

                System.out.println("---------------------------------");
                System.out.println(
                        "History ID  : " +
                                resultSet.getInt("history_id")
                );

                System.out.println(
                        "Incident ID : " +
                                resultSet.getInt("incident_id")
                );

                System.out.println(
                        "Old Status  : " +
                                resultSet.getString("old_status")
                );

                System.out.println(
                        "New Status  : " +
                                resultSet.getString("new_status")
                );

                System.out.println(
                        "Changed At  : " +
                                resultSet.getTimestamp("changed_at")
                );
            }

            if (!found) {
                System.out.println(
                        "No status history found."
                );
            }

            System.out.println("---------------------------------");

        } catch (Exception e) {

            System.out.println(
                    "Error loading incident history: "
                            + e.getMessage()
            );
        }
    }

    private Incident mapIncident(ResultSet resultSet)
            throws Exception {

        int id = resultSet.getInt("id");

        String title =
                resultSet.getString("title");

        String description =
                resultSet.getString("description");

        Category category =
                Category.valueOf(
                        resultSet.getString("category")
                );

        Severity severity =
                Severity.valueOf(
                        resultSet.getString("severity")
                );

        Status status =
                Status.valueOf(
                        resultSet.getString("status")
                );

        Incident incident =
                new Incident(
                        id,
                        title,
                        description,
                        category,
                        severity
                );

        incident.setStatus(status);

        return incident;
    }
}