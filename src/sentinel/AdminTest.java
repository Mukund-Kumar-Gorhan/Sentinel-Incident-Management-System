package sentinel;

import sentinel.model.Category;
import sentinel.model.Incident;
import sentinel.model.Severity;
import sentinel.service.AdminService;

public class AdminTest {

    public static void main(String[] args) {

        AdminService admin = new AdminService();

        System.out.println("=================================");
        System.out.println("       SENTINEL ADMIN TEST");
        System.out.println("=================================");

        // Add Incident
        Incident incident = new Incident(
                2001,
                "Database Server Down",
                "Hospital database server is not responding",
                Category.SYSTEM_FAILURE,
                Severity.CRITICAL
        );

        admin.addIncident(incident);

        System.out.println("\nIncident added successfully.");

        // View Incident
        System.out.println("\n=================================");
        System.out.println("       VIEW INCIDENT");
        System.out.println("=================================");

        System.out.println(admin.findIncident(2001));

        // Total Incidents
        System.out.println("\nTotal incidents: "
                + admin.getTotalIncidents());

        // Delete Incident
        System.out.println("\n=================================");
        System.out.println("       DELETE INCIDENT");
        System.out.println("=================================");

        boolean deleted = admin.deleteIncident(2001);

        System.out.println("Incident deleted: " + deleted);

        // Final Count
        System.out.println("\nFinal total incidents: "
                + admin.getTotalIncidents());

        System.out.println("\n=================================");
        System.out.println("       ADMIN TEST COMPLETE");
        System.out.println("=================================");
    }
}