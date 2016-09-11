package roadgraph.searchalgorithms.api;

import geography.GeographicPoint;
import roadgraph.graphsenities.MapNode;

import java.util.List;
import java.util.function.Consumer;

public interface Searcher {
    List<GeographicPoint> search(MapNode startNode, MapNode goalNode, Consumer<GeographicPoint> nodeSearched);
}
