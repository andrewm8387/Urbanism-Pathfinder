package Resources;

import Resources.Graph.AdjacencyList;
import Resources.Graph.Edge;
import Resources.Graph.Node;

import java.util.*;

public class BreadthFirstSearch implements Searchable {

    @Override
    public List<Node> findShortestPath(AdjacencyList graph, Node start, Node end) {
        return BFS(graph, start, end);
    }

    //Breadth First Search
    private static List<Node> BFS(AdjacencyList graph, Node start, Node end) {
        List<Node> path = new ArrayList<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        Map<Node, Node> parent = new HashMap<Node, Node>();
        Set<Node> visited = new HashSet<Node>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current == end) {
                while (current != start) {
                    path.add(current);
                    current = parent.get(current);
                }
                path.add(start);
                Collections.reverse(path);
                return path;
            }
            for (Edge e : graph.getEdges(current.getId())) {
                Node next = e.getEnd();
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }
        return null;
    }

}
