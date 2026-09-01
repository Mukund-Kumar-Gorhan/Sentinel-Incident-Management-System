package sentinel.service;

import sentinel.model.Incident;
import sentinel.repository.IncidentRepository;
import sentinel.util.IncidentValidator;

import java.util.List;

public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService() {
        repository = new IncidentRepository();
    }

    public void addIncident(Incident incident) {

        IncidentValidator.validate(incident);

        if (repository.existsById(incident.getId())) {
            throw new IllegalArgumentException(
                    "Incident ID already exists: " + incident.getId()
            );
        }

        repository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public Incident findById(int id) {
        return repository.findById(id);
    }

    public boolean removeById(int id) {
        return repository.deleteById(id);
    }

    public int getIncidentCount() {
        return repository.count();
    }

    public boolean updateStatus(int id, sentinel.model.Status status) {
        return repository.updateStatus(id, status);
    }
}