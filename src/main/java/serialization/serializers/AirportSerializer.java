package serialization.serializers;

import common.Serializer;
import domain.Airport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import serialization.parsers.AirportParser;

import java.util.ArrayList;
import java.util.List;

public class AirportSerializer implements Serializer<Airport, Node> {
    private Document document;

    public AirportSerializer(Document document) {
        this.document = document;
    }

    @Override
    public List<Node> serializeMore(List<Airport> input) {
        List<Node> nodes = new ArrayList<>();
        for(Airport p: input)
            nodes.add(serializeOne(p));

        return nodes;
    }

    @Override
    public Node serializeOne(Airport input) {
        Element airportElement = document.createElement("airport");
        Node node = document.createTextNode("name");
        node.setTextContent(input.getName());

        Element location = document.createElement("location");
        location.setAttribute("x", input.getLocation().getX() + "");
        location.setAttribute("y", input.getLocation().getY() + "");

        Element country = document.createElement("country");
        country.setAttribute("code", input.getCountry().getCode());
        country.setTextContent(input.getCountry().getName());

        airportElement.appendChild(node);
        airportElement.appendChild(location);
        airportElement.appendChild(country);

        return airportElement;
    }
}
