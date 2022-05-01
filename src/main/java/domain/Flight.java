package domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private Airport startAirport;
    private Airport endAirport;
    private List<FlightTrade> trades;
    private LocalDateTime leavingAt;
    private LocalDateTime arrivingAt;

    public Flight(Airport startAirport, Airport endAirport, int price, LocalDateTime arrivingAt, LocalDateTime leavingAt) {
        this.startAirport = startAirport;
        this.endAirport = endAirport;
        trades = new ArrayList<>();
        trades.add(new FlightTrade(startAirport, endAirport, price, leavingAt, arrivingAt));
    }

    public Flight(Airport startAirport, Airport endAirport, List<FlightTrade> trades, LocalDateTime leavingAt, LocalDateTime arrivingAt) {
        this.startAirport = startAirport;
        this.endAirport = endAirport;
        this.trades = trades;
        this.leavingAt = leavingAt;
        this.arrivingAt = arrivingAt;
    }

    public Duration getEstimatedDuration() {
        return Duration.between(leavingAt, arrivingAt);
    }

    public int getTotalCost() {
        int sum = 0;

        for(FlightTrade t: trades)
            sum += t.getCost();

        return sum;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "startAirport=" + startAirport +
                ", endAirport=" + endAirport +
                ", trades=" + trades +
                ", leavingAt=" + leavingAt +
                ", arrivingAt=" + arrivingAt +
                '}';
    }
}
