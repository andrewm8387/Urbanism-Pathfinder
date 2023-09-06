package Resources.Graph;

import java.util.Map;

public class Node {
    private final int id;
    private Map<String, Object> attributes = null;

    public Node(int id) {
        this.id = id;
    }

    public Node(int id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
