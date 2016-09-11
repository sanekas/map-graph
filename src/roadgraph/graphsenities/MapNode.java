package roadgraph.graphsenities;


import geography.GeographicPoint;

public class MapNode implements Comparable<MapNode> {
    private final GeographicPoint location;
    private Double weight;

    public MapNode(GeographicPoint location) {
        this.location = location;
        this.weight = Double.POSITIVE_INFINITY;
    }

    public GeographicPoint getLocation() {
        return location;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public double getDist(MapNode node) {
        return location.distance(node.getLocation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapNode mapNode = (MapNode) o;

        return location.equals(mapNode.location);

    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }

    @Override
    public int compareTo(MapNode o) {
        return weight.compareTo(o.getWeight());
    }

    @Override
    public String toString() {
        return "MapNode{" +
                "location=" + location +
                '}';
    }
}
