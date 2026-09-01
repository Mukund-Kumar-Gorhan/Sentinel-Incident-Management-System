package sentinel.util;

import sentinel.model.Incident;

public class IncidentValidator {

    public static void validate(Incident incident) {

        if (incident == null) {
            throw new IllegalArgumentException(
                    "Incident cannot be null."
            );
        }

        if (incident.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Incident ID must be greater than 0."
            );
        }

        if (incident.getTitle() == null ||
                incident.getTitle().isBlank()) {

            throw new IllegalArgumentException(
                    "Incident title cannot be empty."
            );
        }

        if (incident.getDescription() == null ||
                incident.getDescription().isBlank()) {

            throw new IllegalArgumentException(
                    "Incident description cannot be empty."
            );
        }

        if (incident.getCategory() == null) {
            throw new IllegalArgumentException(
                    "Incident category is required."
            );
        }

        if (incident.getSeverity() == null) {
            throw new IllegalArgumentException(
                    "Incident severity is required."
            );
        }

        if (incident.getStatus() == null) {
            throw new IllegalArgumentException(
                    "Incident status is required."
            );
        }
    }
}