package roadgraph;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;

import java.util.*;
import java.util.function.Consumer;

public class DijkstraSearchAlgorithm {
    private final Map<MapNode, List<MapEdge>> graph;;

    public DijkstraSearchAlgorithm(Map<MapNode, List<MapEdge>> graph) {
        this.graph = graph;
    }

    public Map<MapNode, List<MapEdge>> getGraph() {
        return graph;
    }

    public List<GeographicPoint> dijkstraSearch(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched) {
        startNode.setWeight(0.0);
        constructPrimaryWeightedGraph(startNode);
        Queue<MapNode> queue = new PriorityQueue<>();
        Set<MapNode> visitedNodes = new HashSet<>();
        Map<MapNode, MapNode> parentMap = new HashMap<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            MapNode currentNode = queue.remove();
            nodeSearched.accept(currentNode.getLocation());
            if (!visitedNodes.contains(currentNode)) {
                visitedNodes.add(currentNode);
                if (currentNode.equals(goalNode)) {
                    return constructPath(parentMap, goalNode);
                }
                for (MapEdge edge : graph.get(currentNode)) {
                    MapNode neighbour = edge.getOtherNode(currentNode);
                    if (!visitedNodes.contains(neighbour) && neighbour.getWeight() > (currentNode.getWeight() + neighbour.getDist(currentNode))) {
                        neighbour.setWeight(currentNode.getWeight() + neighbour.getDist(currentNode));
                        queue.add(neighbour);
                        parentMap.put(neighbour, currentNode);
                    }
                }

            }
        }
        return null;
    }

    private void constructPrimaryWeightedGraph(MapNode startNode) {
        for (Map.Entry<MapNode, List<MapEdge>> entry : graph.entrySet()) {
            if (entry.getKey().equals(startNode)) {
                entry.getKey().setWeight(0.0);
            }
        }

    }

    private List<GeographicPoint> constructPath(Map<MapNode, MapNode> directions, MapNode goal) {
        List<GeographicPoint> result = new ArrayList<>();
        for(MapNode node = goal; node != null; node = directions.get(node)) {
            result.add(node.getLocation());
        }
        Collections.reverse(result);
        return result;
    }
}
