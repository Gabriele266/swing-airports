import domain.Airport;
import org.w3c.dom.Document;
import serialization.threads.AirportParserThread;
import serialization.threads.AirportSerializerThread;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AirportParserThread thread = new AirportParserThread(args[0]);

        thread.start();
        try {
            thread.join();

            List<Airport> airports = thread.getResult();
            System.out.println(airports);

            AirportSerializerThread thread1 = new AirportSerializerThread(airports);
            thread1.start();
            thread1.join();

            Document doc = thread1.getResult();
            Transformer trs = TransformerFactory.newInstance().newTransformer();
            trs.transform(
                    new DOMSource(doc), new StreamResult(
                            new FileOutputStream("out.xml")));

        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        } catch (TransformerConfigurationException e) {
            System.out.println("Xml transformer malconfigured");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}
