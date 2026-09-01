package sentinel.service;

import sentinel.model.Category;
import sentinel.model.Incident;
import sentinel.model.Severity;
import sentinel.model.Status;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class IncidentStatistics {

    public Map<Severity, Integer> countBySeverity(
            List<Incident> incidents) {

        Map<Severity, Integer> result =
                new EnumMap<>(Severity.class);

        for (Severity severity : Severity.values()) {
            result.put(severity, 0);
        }

        for (Incident incident : incidents) {
            Severity severity = incident.getSeverity();

            result.put(
                    severity,
                    result.get(severity) + 1
            );
        }

        return result;
    }


    public Map<Status, Integer> countByStatus(
            List<Incident> incidents) {

        Map<Status, Integer> result =
                new EnumMap<>(Status.class);

        for (Status status : Status.values()) {
            result.put(status, 0);
        }

        for (Incident incident : incidents) {
            Status status = incident.getStatus();

            result.put(
                    status,
                    result.get(status) + 1
            );
        }

        return result;
    }


    public Map<Category, Integer> countByCategory(
            List<Incident> incidents) {

        Map<Category, Integer> result =
                new EnumMap<>(Category.class);

        for (Category category : Category.values()) {
            result.put(category, 0);
        }

        for (Incident incident : incidents) {
            Category category = incident.getCategory();

            result.put(
                    category,
                    result.get(category) + 1
            );
        }

        return result;
    }


    public double averageIncidentId(
            List<Incident> incidents) {

        if (incidents.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (Incident incident : incidents) {
            total += incident.getId();
        }

        return (double) total / incidents.size();
    }
}