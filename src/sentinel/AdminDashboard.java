package sentinel;

import sentinel.model.Category;
import sentinel.model.Incident;
import sentinel.model.Severity;
import sentinel.model.Status;
import sentinel.model.User;
import sentinel.repository.IncidentRepository;
import sentinel.service.IncidentService;

import java.util.List;
import java.util.Scanner;

public class AdminDashboard {

    private final User admin;
    private final IncidentService incidentService;
    private final IncidentRepository repository;
    private final Scanner scanner;

    public AdminDashboard(User admin) {

        this.admin = admin;
        this.incidentService = new IncidentService();
        this.repository = new IncidentRepository();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        ADMIN DASHBOARD");
            System.out.println("=================================");
            System.out.println("Welcome, " + admin.getUsername());
            System.out.println("---------------------------------");
            System.out.println("1. View All Incidents");
            System.out.println("2. Search Incident");
            System.out.println("3. Add Incident");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Delete Incident");
            System.out.println("6. Total Incidents");
            System.out.println("7. View Incident History");
            System.out.println("8. Logout");
            System.out.println("---------------------------------");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    viewAllIncidents();
                    break;

                case "2":
                    searchIncident();
                    break;

                case "3":
                    addIncident();
                    break;

                case "4":
                    updateIncidentStatus();
                    break;

                case "5":
                    deleteIncident();
                    break;

                case "6":
                    showTotalIncidents();
                    break;

                case "7":
                    viewIncidentHistory();
                    break;

                case "8":
                    System.out.println("\nLogging out...");
                    System.out.println(
                            "Admin logged out successfully."
                    );
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    private void viewAllIncidents() {

        System.out.println("\n=================================");
        System.out.println("        ALL INCIDENTS");
        System.out.println("=================================");

        List<Incident> incidents =
                incidentService.getAllIncidents();

        if (incidents.isEmpty()) {
            System.out.println("No incidents found.");
            return;
        }

        for (Incident incident : incidents) {

            System.out.println("---------------------------------");
            System.out.println("ID          : " + incident.getId());
            System.out.println("Title       : " + incident.getTitle());
            System.out.println(
                    "Description : " + incident.getDescription()
            );
            System.out.println(
                    "Category    : " + incident.getCategory()
            );
            System.out.println(
                    "Severity    : " + incident.getSeverity()
            );
            System.out.println(
                    "Status      : " + incident.getStatus()
            );
        }

        System.out.println("---------------------------------");
    }

    private void searchIncident() {

        System.out.println("\n=================================");
        System.out.println("        SEARCH INCIDENT");
        System.out.println("=================================");

        System.out.print("Enter Incident ID: ");

        try {

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    incidentService.findById(id);

            if (incident == null) {

                System.out.println("Incident not found.");
                return;
            }

            System.out.println("\nIncident Found");
            System.out.println("---------------------------------");
            System.out.println(
                    "ID          : " + incident.getId()
            );
            System.out.println(
                    "Title       : " + incident.getTitle()
            );
            System.out.println(
                    "Description : " + incident.getDescription()
            );
            System.out.println(
                    "Category    : " + incident.getCategory()
            );
            System.out.println(
                    "Severity    : " + incident.getSeverity()
            );
            System.out.println(
                    "Status      : " + incident.getStatus()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID. Please enter a number."
            );
        }
    }

    private void addIncident() {

        System.out.println("\n=================================");
        System.out.println("         ADD INCIDENT");
        System.out.println("=================================");

        try {

            System.out.print("Enter ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Category: ");
            String categoryInput = scanner.nextLine();

            System.out.print("Enter Severity: ");
            String severityInput = scanner.nextLine();

            Category category =
                    Category.valueOf(
                            categoryInput.toUpperCase()
                    );

            Severity severity =
                    Severity.valueOf(
                            severityInput.toUpperCase()
                    );

            Incident incident =
                    new Incident(
                            id,
                            title,
                            description,
                            category,
                            severity
                    );

            incidentService.addIncident(incident);

            System.out.println(
                    "Incident added successfully."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID. Please enter a number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid category, severity, or incident data."
            );

            System.out.println(
                    "Details: " + e.getMessage()
            );
        }
    }

    private void updateIncidentStatus() {

        System.out.println("\n=================================");
        System.out.println("     UPDATE INCIDENT STATUS");
        System.out.println("=================================");

        try {

            System.out.print("Enter Incident ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    incidentService.findById(id);

            if (incident == null) {

                System.out.println("Incident not found.");
                return;
            }

            System.out.println(
                    "Current Status: " +
                            incident.getStatus()
            );

            System.out.print("Enter New Status: ");

            String statusInput =
                    scanner.nextLine();

            Status status =
                    Status.valueOf(
                            statusInput.toUpperCase()
                    );

            boolean updated =
                    repository.updateStatus(
                            id,
                            status
                    );

            if (updated) {

                System.out.println(
                        "Incident status updated successfully."
                );

            } else {

                System.out.println(
                        "Unable to update incident status."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID. Please enter a number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid status."
            );
        }
    }

    private void deleteIncident() {

        System.out.println("\n=================================");
        System.out.println("        DELETE INCIDENT");
        System.out.println("=================================");

        try {

            System.out.print("Enter Incident ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    incidentService.findById(id);

            if (incident == null) {

                System.out.println("Incident not found.");
                return;
            }

            System.out.println(
                    "Incident: " +
                            incident.getTitle()
            );

            System.out.print(
                    "Are you sure you want to delete it? (yes/no): "
            );

            String confirmation =
                    scanner.nextLine();

            if (!confirmation.equalsIgnoreCase("yes")) {

                System.out.println(
                        "Delete cancelled."
                );

                return;
            }

            boolean deleted =
                    incidentService.removeById(id);

            if (deleted) {

                System.out.println(
                        "Incident deleted successfully."
                );

            } else {

                System.out.println(
                        "Unable to delete incident."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID. Please enter a number."
            );
        }
    }

    private void showTotalIncidents() {

        System.out.println("\n=================================");
        System.out.println("        TOTAL INCIDENTS");
        System.out.println("=================================");

        int count =
                incidentService.getIncidentCount();

        System.out.println(
                "Total Incidents: " + count
        );
    }

    private void viewIncidentHistory() {

        System.out.println("\n=================================");
        System.out.println("       INCIDENT HISTORY");
        System.out.println("=================================");

        try {

            System.out.print("Enter Incident ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    incidentService.findById(id);

            if (incident == null) {

                System.out.println(
                        "Incident not found."
                );

                return;
            }

            repository.showIncidentHistory(id);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid ID. Please enter a number."
            );
        }
    }
}