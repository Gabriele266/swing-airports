package domain;

import java.awt.geom.Point2D;

public class Airport {
    private String name;
    private Point2D location;
    private Country country;

    public Airport() {

    }

    public Airport(String name, Point2D location, Country country) {
        this.name = name;
        this.location = location;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", country=" + country +
                '}';
    }
}
