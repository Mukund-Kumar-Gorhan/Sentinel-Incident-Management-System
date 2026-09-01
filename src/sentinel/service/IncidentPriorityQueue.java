package sentinel.service;

import sentinel.model.Incident;
import sentinel.model.Severity;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IncidentPriorityQueue {

    private final PriorityQueue<Incident> queue;

    public IncidentPriorityQueue() {

        Comparator<Incident> comparator = (a, b) ->
                Integer.compare(
                        getPriority(b.getSeverity()),
                        getPriority(a.getSeverity())
                );

        queue = new PriorityQueue<>(comparator);
    }

    private int getPriority(Severity severity) {

        return switch (severity) {
            case CRITICAL -> 4;
            case HIGH -> 3;
            case MEDIUM -> 2;
            case LOW -> 1;
        };
    }

    public void add(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException(
                    "Incident cannot be null"
            );
        }

        queue.offer(incident);
    }

    public Incident getNext() {
        return queue.poll();
    }

    public Incident peekNext() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}