package sentinel.model;

public class Incident {

    private int id;
    private String title;
    private String description;
    private Category category;
    private Severity severity;
    private Status status;

    public Incident(int id, String title, String description,
                    Category category, Severity severity) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.severity = severity;
        this.status = Status.OPEN;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", severity=" + severity +
                ", status=" + status +
                '}';
    }
}