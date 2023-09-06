package Resources;

import Resources.Graph.AdjacencyList;
import Resources.Graph.Node;

import java.util.List;

public interface Searchable {
    public List<Node> findShortestPath(AdjacencyList graph, Node start, Node end);
}
