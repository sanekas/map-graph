package roadgraph;


import geography.GeographicPoint;

public class MapNode {
    private final GeographicPoint location;

    public MapNode(GeographicPoint location) {
        this.location = location;
    }

    public GeographicPoint getLocation() {
        return location;
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
}
