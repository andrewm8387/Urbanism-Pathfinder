package Resources.Graph;

import java.util.*;


public class AdjacencyList extends Graph {
    private final ArrayList<ArrayList<Edge>> AdjacencyList = new ArrayList<>();

    public AdjacencyList(Set<Node> Nodes, Set<Edge> Edges) {
        super(Nodes, Edges);
        //Initialize adjacency list
        for (int i = 0; i < Nodes.size(); i++) {
            AdjacencyList.add(new ArrayList<>());
        }
        for (Edge e : Edges) { //Add edges to adjacency list representation
            AdjacencyList.get(e.getStart().getId()).add(e);
            AdjacencyList.get(e.getEnd().getId()).add(new Edge(e.getEnd(), e.getStart()));
        }
    }

    public ArrayList<Edge> getEdges(int id) {
        return AdjacencyList.get(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adjacency List Representation:\n");
        for (List<Edge> list : AdjacencyList) {
            sb.append(list.get(0).getStart().getId()).append(" -> ");
            for (Edge e : list) {
                sb.append(e.getEnd().getId()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
