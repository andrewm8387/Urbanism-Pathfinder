package Resources.Graph;

import java.util.HashSet;
import java.util.Set;

public abstract class Graph {
    private Set<Edge> Edges = new HashSet<Edge>();
    private Set<Node> Nodes = new HashSet<Node>();
    private Node centralNode;

    public Graph(Set<Node> Nodes, Set<Edge> Edges) {
        this.Edges = new HashSet<Edge>(Edges);
        this.Nodes = new HashSet<Node>(Nodes);
    }

    public Set<Node> getNodes() {
        return Nodes;
    }

    public void setCentralNode(Node centralNode) {
        this.centralNode = centralNode;
    }

    public Node getCentralNode() {
        return centralNode;
    }
}

