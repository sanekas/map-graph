package roadgraph.searchalgorithms.impl;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;
import roadgraph.searchalgorithms.api.SearchAlgo;

import java.util.*;
import java.util.function.Consumer;

public class DijkstraAlgo extends SearchAlgo {

    public DijkstraAlgo(Map<MapNode, List<MapEdge>> graph) {
        super(graph);
    }

    public Map<MapNode, List<MapEdge>> getGraph() {
        return graph;
    }

    @Override
    public boolean findPath(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched,
                            List<GeographicPoint> result) {
        if (isInputValid(startNode, goalNode, nodeSearched, result)) {
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
                        constructPath(parentMap, goalNode, result);
                        return true;
                    }
                    for (MapEdge edge : graph.get(currentNode)) {
                        MapNode neighbour = edge.getOtherNode(currentNode);
                        double newWeight = currentNode.getWeight() + edge.getLength();
                        if (!visitedNodes.contains(neighbour) && neighbour.getWeight() > newWeight) {
                            neighbour.setWeight(newWeight);
                            parentMap.put(neighbour, currentNode);
                            queue.add(neighbour);
                        }
                    }

                }
            }
            return false;
        } else  {
            return false;
        }
    }

    private void constructPrimaryWeightedGraph(MapNode startNode) {
        for (Map.Entry<MapNode, List<MapEdge>> entry : graph.entrySet()) {
            if (entry.getKey().equals(startNode)) {
                entry.getKey().setWeight(0.0);
            }
        }
    }
}
