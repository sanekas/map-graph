package roadgraph.searchalgorithms.impl;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;
import roadgraph.searchalgorithms.api.SearchAlgo;

import java.util.*;
import java.util.function.Consumer;

public class AStarAlgo extends SearchAlgo {
    public AStarAlgo(Map<MapNode, List<MapEdge>> graph) {
        super(graph);
    }

    @Override
    public boolean findPath(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched, List<GeographicPoint> result) {
        if (isInputValid(startNode, goalNode, nodeSearched, result)) {
            constructPrimaryWeightedGraph(startNode);
            Queue<MapNode> queue = new PriorityQueue<>();
            Set<MapNode> visitedNodes = new HashSet<>();
            Map<MapNode, MapNode> parentMap = new HashMap<>();
            queue.add(startNode);
            int counter = 0;
            while (!queue.isEmpty()) {
                MapNode currentNode = queue.remove();
                counter++;
                nodeSearched.accept(currentNode.getLocation());
                if (!visitedNodes.contains(currentNode)) {
                    visitedNodes.add(currentNode);
                    if (currentNode.equals(goalNode)) {
                        System.out.println("A* Algorithm visit: " + counter);
                        constructPath(parentMap, goalNode, result);
                        return true;
                    }
                    for (MapEdge edge : graph.get(currentNode)) {
                        MapNode neighbour = edge.getOtherNode(currentNode);
                        double newWeight = currentNode.getWeight() + edge.getLength() + currentNode.getDist(goalNode);
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

}
