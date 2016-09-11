package roadgraph.searchalgorithms.impl;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;
import roadgraph.searchalgorithms.api.SearchAlgo;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AStarAlgo extends SearchAlgo {
    public AStarAlgo(Map<MapNode, List<MapEdge>> graph) {
        super(graph);
    }

    @Override
    public boolean findPath(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched, List<GeographicPoint> result) {
        return false;
    }

}
