package serialization.threads;

import domain.Airport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import serialization.parsers.AirportParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportParserThread extends Thread {
    private String sourceFile;
    private volatile List<Airport> result = null;
    private volatile boolean resultReady = false;

    public AirportParserThread(String sourceFile) {
        super("AirportParserThread");
        this.sourceFile = sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public boolean hasResult() {
        return resultReady;
    }

    public List<Airport> getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            AirportParser parser = new AirportParser();

            Document document = builder.parse(
                    new File(sourceFile));

            Element airports = document.getDocumentElement();
            airports.normalize();
            NodeList nodes = document.getElementsByTagName("airport");

            result = new ArrayList<>();

            for (int x = 0; x < nodes.getLength(); x++) {
                result.add(parser.parseOne(nodes.item(x)));
            }

            resultReady = true;

        } catch (ParserConfigurationException ext) {
            System.out.println(ext);
        } catch (SAXException exc) {
            System.out.println(exc);
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }
}
