package domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class FlightTrade {
    private Airport from;
    private Airport to;
    private int cost;
    private LocalDateTime estimatedLeave;
    private LocalDateTime estimatedArrival;

    public FlightTrade(Airport from, Airport to) {
        this.from = from;
        this.to = to;
    }

    public FlightTrade(Airport from, Airport to, int cost, LocalDateTime estimatedLeave, LocalDateTime estimatedArrival) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.estimatedLeave = estimatedLeave;
        this.estimatedArrival = estimatedArrival;
    }

    public FlightTrade() {
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDateTime getEstimatedLeave() {
        return estimatedLeave;
    }

    public void setEstimatedLeave(LocalDateTime estimatedLeave) {
        this.estimatedLeave = estimatedLeave;
    }

    public LocalDateTime getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(LocalDateTime estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public Duration getEstimatedDuration() {
        return Duration.between(estimatedLeave, estimatedArrival);
    }
}
