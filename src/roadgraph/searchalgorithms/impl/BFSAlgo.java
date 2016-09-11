package roadgraph.searchalgorithms.impl;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;
import roadgraph.searchalgorithms.api.SearchAlgo;

import java.util.*;
import java.util.function.Consumer;

public class BFSAlgo extends SearchAlgo {
    public BFSAlgo(Map<MapNode, List<MapEdge>> graph) {
        super(graph);
    }

    @Override
    public boolean findPath(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched,
                            List<GeographicPoint> result) {
        if (isInputValid(startNode, goalNode, nodeSearched, result)) {
            Queue<MapNode> queue = new LinkedList<>();
            Set<MapNode> visitedVertices = new HashSet<>();
            Map<MapNode, MapNode> parentMap = new HashMap<>();
            visitedVertices.add(startNode);
            queue.add(startNode);
            nodeSearched.accept(startNode.getLocation());
            while (!queue.isEmpty()) {
                MapNode current = queue.remove();
                if (current.equals(goalNode)) {
                    constructPath(parentMap, goalNode, result);
                    return true;
                } else {
                    for (MapEdge edge : graph.get(current)) {
                        MapNode neighbour = edge.getOtherNode(current);
                        if (!visitedVertices.contains(neighbour)) {
                            queue.add(neighbour);
                            visitedVertices.add(neighbour);
                            parentMap.put(neighbour, current);
                            nodeSearched.accept(neighbour.getLocation());
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
