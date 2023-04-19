package serialization.parsers;

import common.Parser;
import domain.Airport;
import domain.Country;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AirportParser implements Parser<Airport, Node> {
    @Override
    synchronized public Airport parseOne(Node node) {
        Element element = (Element) node;

        Airport p = new Airport();
        p.setName(element.getElementsByTagName("name").item(0).getTextContent());

        Element pointElement = (Element) element.getElementsByTagName("location").item(0);
        Point point = new Point();
        point.x = Integer.parseInt(pointElement.getAttribute("x"));
        point.y = Integer.parseInt(pointElement.getAttribute("y"));
        p.setLocation(point);

        Element countryElement = (Element) element.getElementsByTagName("country").item(0);
        p.setCountry(
                new Country(
                        countryElement.getTextContent(), countryElement.getAttribute("code")));

        return p;
    }

    @Override
    public List<Airport> parseMore(List<Node> nodes) {
        List<Airport> airports = new ArrayList<>();
        for (Node n : nodes)
            airports.add(parseOne(n));

        return airports;
    }
}
