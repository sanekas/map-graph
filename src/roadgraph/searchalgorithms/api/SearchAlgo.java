package roadgraph.searchalgorithms.api;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapEdge;
import roadgraph.graphsenities.MapNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class SearchAlgo implements Searcher {
    protected final Map<MapNode, List<MapEdge>> graph;

    public SearchAlgo(Map<MapNode, List<MapEdge>> graph) {
        this.graph = graph;
    }

    public Map<MapNode, List<MapEdge>> getGraph() {
        return graph;
    }



    public abstract boolean findPath(MapNode startNode, MapNode goalNode,
                                     Consumer<GeographicPoint> nodeSearched, List<GeographicPoint> result);

    public List<GeographicPoint> search(MapNode startNode, MapNode goalNode,
                                        Consumer<GeographicPoint> nodeSearched) {
        List<GeographicPoint> result = new ArrayList<>();
        return findPath(startNode, goalNode, nodeSearched, result) ? result : null;
    }

    protected boolean isInputValid(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched,
                                   List<GeographicPoint> result) {

        return startNode != null  && goalNode != null && graph != null && result != null && nodeSearched != null;
    }

    protected List<GeographicPoint> constructPath(Map<MapNode, MapNode> parentMap, MapNode goal,
                                                List<GeographicPoint> result) {
        for(MapNode node = goal; node != null; node = parentMap.get(node)) {
            result.add(node.getLocation());
        }
        Collections.reverse(result);
        return result;
    }

}
