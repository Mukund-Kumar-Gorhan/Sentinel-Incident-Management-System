package sentinel;

import sentinel.model.Category;
import sentinel.model.Incident;
import sentinel.model.Severity;
import sentinel.model.User;
import sentinel.repository.IncidentHistoryRepository;
import sentinel.repository.IncidentRepository;
import sentinel.service.IncidentService;

import java.util.List;
import java.util.Scanner;

public class UserDashboard {

    private final User user;
    private final Scanner scanner;
    private final IncidentService incidentService;
    private final IncidentRepository repository;
    private final IncidentHistoryRepository historyRepository;

    public UserDashboard(User user) {

        this.user = user;
        this.scanner = new Scanner(System.in);
        this.incidentService = new IncidentService();
        this.repository = new IncidentRepository();
        this.historyRepository = new IncidentHistoryRepository();
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("         USER DASHBOARD");
            System.out.println("=================================");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("---------------------------------");
            System.out.println("1. View All Incidents");
            System.out.println("2. Search Incident");
            System.out.println("3. Report Incident");
            System.out.println("4. View Incident History");
            System.out.println("5. View Profile");
            System.out.println("6. Logout");
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
                    reportIncident();
                    break;

                case "4":
                    viewIncidentHistory();
                    break;

                case "5":
                    viewProfile();
                    break;

                case "6":
                    System.out.println("\nLogging out...");
                    System.out.println("Logged out successfully.");
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // ==========================================
    // VIEW ALL INCIDENTS
    // ==========================================

    private void viewAllIncidents() {

        System.out.println("\n=================================");
        System.out.println("        ALL INCIDENTS");
        System.out.println("=================================");

        List<Incident> incidents =
                repository.findAll();

        if (incidents.isEmpty()) {

            System.out.println("No incidents found.");
            return;
        }

        for (Incident incident : incidents) {
            System.out.println(incident);
        }
    }

    // ==========================================
    // SEARCH INCIDENT
    // ==========================================

    private void searchIncident() {

        System.out.println("\n=================================");
        System.out.println("        SEARCH INCIDENT");
        System.out.println("=================================");

        System.out.print("Enter Incident ID: ");

        try {

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    repository.findById(id);

            if (incident != null) {

                System.out.println("\nIncident Found:");
                System.out.println(incident);

            } else {

                System.out.println(
                        "Incident not found."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid ID."
            );
        }
    }

    // ==========================================
    // REPORT INCIDENT
    // ==========================================

    private void reportIncident() {

        System.out.println("\n=================================");
        System.out.println("        REPORT INCIDENT");
        System.out.println("=================================");

        try {

            System.out.print("Enter incident ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            if (repository.existsById(id)) {

                System.out.println(
                        "This Incident ID already exists."
                );

                return;
            }

            System.out.print("Enter title: ");
            String title =
                    scanner.nextLine();

            System.out.print("Enter description: ");
            String description =
                    scanner.nextLine();

            System.out.println("\nSelect Category:");
            System.out.println("1. SYSTEM_FAILURE");
            System.out.println("2. SOFTWARE_BUG");
            System.out.println("3. NETWORK_ISSUE");
            System.out.println("4. SECURITY_INCIDENT");

            System.out.print("Enter choice: ");

            int categoryChoice =
                    Integer.parseInt(scanner.nextLine());

            Category category;

            switch (categoryChoice) {

                case 1:
                    category =
                            Category.SYSTEM_FAILURE;
                    break;

                case 2:
                    category =
                            Category.SOFTWARE_BUG;
                    break;

                case 3:
                    category =
                            Category.NETWORK_ISSUE;
                    break;

                case 4:
                    category =
                            Category.SECURITY_INCIDENT;
                    break;

                default:
                    System.out.println(
                            "Invalid category."
                    );
                    return;
            }

            System.out.println("\nSelect Severity:");
            System.out.println("1. LOW");
            System.out.println("2. MEDIUM");
            System.out.println("3. HIGH");
            System.out.println("4. CRITICAL");

            System.out.print("Enter choice: ");

            int severityChoice =
                    Integer.parseInt(scanner.nextLine());

            Severity severity;

            switch (severityChoice) {

                case 1:
                    severity =
                            Severity.LOW;
                    break;

                case 2:
                    severity =
                            Severity.MEDIUM;
                    break;

                case 3:
                    severity =
                            Severity.HIGH;
                    break;

                case 4:
                    severity =
                            Severity.CRITICAL;
                    break;

                default:
                    System.out.println(
                            "Invalid severity."
                    );
                    return;
            }

            Incident incident =
                    new Incident(
                            id,
                            title,
                            description,
                            category,
                            severity
                    );

            incidentService.addIncident(incident);

            System.out.println("\n=================================");
            System.out.println("       INCIDENT REPORTED");
            System.out.println("=================================");
            System.out.println(
                    "Incident ID : " + id
            );
            System.out.println(
                    "Title       : " + title
            );
            System.out.println(
                    "Category    : " + category
            );
            System.out.println(
                    "Severity    : " + severity
            );
            System.out.println(
                    "Status      : OPEN"
            );
            System.out.println("---------------------------------");
            System.out.println(
                    "Incident reported successfully!"
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values."
            );

        } catch (Exception e) {

            System.out.println(
                    "Unable to report incident: "
                            + e.getMessage()
            );
        }
    }

    // ==========================================
    // VIEW INCIDENT HISTORY
    // ==========================================

    private void viewIncidentHistory() {

        System.out.println("\n=================================");
        System.out.println("       INCIDENT HISTORY");
        System.out.println("=================================");

        try {

            System.out.print("Enter Incident ID: ");

            int id =
                    Integer.parseInt(scanner.nextLine());

            Incident incident =
                    repository.findById(id);

            if (incident == null) {

                System.out.println(
                        "Incident not found."
                );

                return;
            }

            historyRepository.showHistory(id);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid ID."
            );
        }
    }

    // ==========================================
    // VIEW PROFILE
    // ==========================================

    private void viewProfile() {

        System.out.println("\n=================================");
        System.out.println("          MY PROFILE");
        System.out.println("=================================");

        System.out.println(
                "Username : " + user.getUsername()
        );

        System.out.println(
                "Email    : " + user.getEmail()
        );

        System.out.println(
                "Role     : " + user.getRole()
        );
    }
}