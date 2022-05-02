import domain.Airport;
import serialization.threads.AirportParserThread;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AirportParserThread thread = new AirportParserThread(args[0]);

        thread.start();
        try {
            thread.join();

            List<Airport> airports = thread.getResult();
            System.out.println(airports);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

    }
}
