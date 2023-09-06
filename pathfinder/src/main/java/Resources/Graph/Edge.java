package Resources.Graph;

import java.util.Map;

public class Edge {
    private final Node start;
    private final Node end;
    Map<String, Object> attributes;

    public Edge(Node start, Node end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Nodes cannot be null");
        }
        this.start = start;
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
