package sentinel.service;

import sentinel.model.Incident;

import java.util.List;

public class AdminService {

    private final IncidentService incidentService;

    public AdminService() {
        incidentService = new IncidentService();
    }

    // View all incidents
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // Add new incident
    public void addIncident(Incident incident) {
        incidentService.addIncident(incident);
    }

    // Find incident
    public Incident findIncident(int id) {
        return incidentService.findById(id);
    }

    // Delete incident
    public boolean deleteIncident(int id) {
        return incidentService.removeById(id);
    }

    // Total incidents
    public int getTotalIncidents() {
        return incidentService.getIncidentCount();
    }
}