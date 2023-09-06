import Resources.BreadthFirstSearch;
import Resources.Graph.AdjacencyList;
import Resources.Searchable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.*;

import Resources.Graph.*;

public class pathfinderTest {
    static Map<Integer, Node> nodes = new HashMap<>();
    static Set<Edge> edges = new HashSet<>();
    static AdjacencyList graph;

    @BeforeAll
    public static void setup() {
        Node n1 = new Node(0);
        Node n2 = new Node(1);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        Node n5 = new Node(4);
        Node n6 = new Node(5);

        nodes.put(n1.getId(), n1);
        nodes.put(n2.getId(), n2);
        nodes.put(n3.getId(), n3);
        nodes.put(n4.getId(), n4);
        nodes.put(n5.getId(), n5);
        nodes.put(n6.getId(), n6);

        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n1, n3);
        Edge e3 = new Edge(n2, n4);
        Edge e4 = new Edge(n3, n2);
        Edge e5 = new Edge(n4, n5);

        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);

        Set<Node> nodes_set = new HashSet<Node>(nodes.values());

        graph = new AdjacencyList(nodes_set, edges);
    }


    @Test
    public void testBreadthFirstSearch() {
        //test with normal case
        Searchable searcher = new BreadthFirstSearch();
        List<Node> path = searcher.findShortestPath(graph, nodes.get(0), nodes.get(4));
        assert path != null;
        assert path.size() == 4;
        assert path.get(0).getId() == 0;
        assert path.get(1).getId() == 1;
        assert path.get(2).getId() == 3;
        assert path.get(3).getId() == 4;

        //test with start and end being the same node
        path = searcher.findShortestPath(graph, nodes.get(0), nodes.get(0));
        assert path != null;
        assert path.size() == 1;
        assert path.get(0).getId() == 0;

        //test with no path
        path = searcher.findShortestPath(graph, nodes.get(0), nodes.get(5));
        assert path == null;
    }
}
