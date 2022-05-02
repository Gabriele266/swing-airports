package serialization.threads;

import domain.Airport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import serialization.serializers.AirportSerializer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public class AirportSerializerThread extends Thread {
    private boolean resultReady = false;
    private Document result = null;
    private List<Airport> buffer;

    public AirportSerializerThread(List<Airport> airports) {
        super("AirportSerializerThread");
        this.buffer = airports;
    }

    @Override
    public void run() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("airports");
            doc.appendChild(rootElement);

            AirportSerializer serializer = new AirportSerializer(doc);

            List<Node> nodes = serializer.serializeMore(buffer);
            nodes.forEach( it -> rootElement.appendChild(it));

            resultReady = true;
            result = doc;
        } catch (ParserConfigurationException e) {
            System.out.println("Error while serializing");
        }
    }

    public Document getResult() {
        return result;
    }
}
