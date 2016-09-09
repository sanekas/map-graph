package roadgraph;


public class MapEdge {
    private final MapNode node1;
    private final MapNode node2;

    private final String roadName;
    private final String roadType;

    private final double length;

    public MapEdge(MapNode node1, MapNode node2, String roadName, String roadType, double length) {
        this.node1 = node1;
        this.node2 = node2;
        this.roadName = roadName;
        this.roadType = roadType;
        this.length = length;
    }

    public MapNode getNode1() {
        return node1;
    }

    public MapNode getNode2() {
        return node2;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getRoadType() {
        return roadType;
    }

    public double getLength() {
        return length;
    }

    public MapNode getOtherNode(MapNode node) {
        if (node.equals(node1)) {
            return node2;
        }

        if (node.equals(node2)) {
            return node1;
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapEdge mapEdge = (MapEdge) o;

        if (Double.compare(mapEdge.length, length) != 0) return false;
        if (!node1.equals(mapEdge.node1)) return false;
        if (!node2.equals(mapEdge.node2)) return false;
        if (!roadName.equals(mapEdge.roadName)) return false;
        return roadType.equals(mapEdge.roadType);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = node1.hashCode();
        result = 31 * result + node2.hashCode();
        result = 31 * result + roadName.hashCode();
        result = 31 * result + roadType.hashCode();
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
