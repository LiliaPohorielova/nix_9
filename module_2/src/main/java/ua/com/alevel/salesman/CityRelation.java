package ua.com.alevel.salesman;

public class CityRelation {

    private final String id;
    private final CityNode source;
    private final CityNode destination;
    private final int weight;

    public CityRelation(String id, CityNode source, CityNode destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public CityNode getDestination() {
        return destination;
    }

    public CityNode getSource() {
        return source;
    }

    public String getDestinationName() {
        return destination.getName();
    }

    public String getSourceName() {
        return source.getName();
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
