package sentinel.service;

import sentinel.model.Incident;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IncidentSearchService {

    // Linear search by title
    public Incident linearSearchByTitle(
            List<Incident> incidents,
            String title) {

        for (Incident incident : incidents) {

            if (incident.getTitle().equalsIgnoreCase(title)) {
                return incident;
            }
        }

        return null;
    }


    // Search by keyword
    public List<Incident> searchByKeyword(
            List<Incident> incidents,
            String keyword) {

        List<Incident> results = new ArrayList<>();

        if (keyword == null || keyword.isBlank()) {
            return results;
        }

        keyword = keyword.toLowerCase();

        for (Incident incident : incidents) {

            if (incident.getTitle()
                    .toLowerCase()
                    .contains(keyword)
                    ||
                    incident.getDescription()
                            .toLowerCase()
                            .contains(keyword)
                    ||
                    incident.getCategory()
                            .name()
                            .toLowerCase()
                            .contains(keyword)) {

                results.add(incident);
            }
        }

        return results;
    }


    // Sort by title
    public List<Incident> sortByTitle(
            List<Incident> incidents) {

        List<Incident> sorted =
                new ArrayList<>(incidents);

        sorted.sort(
                Comparator.comparing(
                        Incident::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sorted;
    }


    // Sort by ID
    public List<Incident> sortById(
            List<Incident> incidents) {

        List<Incident> sorted =
                new ArrayList<>(incidents);

        sorted.sort(
                Comparator.comparingInt(
                        Incident::getId
                )
        );

        return sorted;
    }
}