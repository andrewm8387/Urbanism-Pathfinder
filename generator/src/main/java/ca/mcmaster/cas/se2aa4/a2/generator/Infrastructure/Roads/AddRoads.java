package ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Roads;

import Resources.*;
import Resources.Graph.*;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Addable;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.CitySize;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.*;

public class AddRoads implements Addable {

    private List<Vertex> vertices;
    private List<PairOfVertex> segments;

    //Everything is just connected to the metropolis

    @Override
    public void add(Mesh mesh) {
        AdjacencyList graph = meshToGraph(mesh);

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        List<Node> path;
        ArrayList<Vertex> stack = new ArrayList<>();

        for (Node node : graph.getNodes()) {

            if (vertices.get(node.getId()).getCitySize().equals(CitySize.NONE)) continue;

            path = bfs.findShortestPath(graph, node, graph.getCentralNode());

//            //print out path
//            StringBuilder pathString = new StringBuilder();
//            for (Node n : path) {
//                pathString.append(n.getId()).append(" ");
//            }

            //convert path of Nodes to stack of vertices
            for (Node n : path) {
                stack.add(vertices.get(n.getId()));
            }

//            //print out stack
//            StringBuilder stackString = new StringBuilder();
//            for (Vertex v : stack) {
//                stackString.append(vertices.indexOf(v)).append(" ");
//            }


            Vertex current = stack.remove(0);
            while (!stack.isEmpty()) {
                Vertex next = stack.remove(0);
                current.setNextVertex(next);
                current = next;
            }
        }
    }

    private AdjacencyList meshToGraph(Mesh mesh) {
        Set<Vertex> verticesSet = new HashSet<>();
        Set<PairOfVertex> segmentsSet = new HashSet<>();
        ArrayList<Vertex> tempVertices = new ArrayList<>();
        for (Polygon p : mesh.getPolygons()) {
            for (Vertex v : p.hullVertices()) {
                if (!verticesSet.add(v)) {
                    for (Vertex vInSet: verticesSet) {
                        if (vInSet.equals(v)) {
                            tempVertices.add(vInSet);
                        }
                    }
                } else {
                    tempVertices.add(v);
                }
            }
            p.setHull(new ArrayList<>(tempVertices));
            tempVertices.clear();
            segmentsSet.addAll(p.hull());
        }

        for (PairOfVertex segment : segmentsSet) {
            if (!verticesSet.contains(segment.contents()[0])) {
                for (Vertex vertex : verticesSet) {
                    if (vertex.equals(segment.contents()[0])) {
                        segment.contents()[0] = vertex;
                    }
                }
            }
            if (!verticesSet.contains(segment.contents()[1])) {
                for (Vertex vertex : verticesSet) {
                    if (vertex.equals(segment.contents()[1])) {
                        segment.contents()[1] = vertex;
                    }
                }
            }
        }

        List<Vertex> vertices = new ArrayList<>(verticesSet);
        List<PairOfVertex> segments = new ArrayList<>(segmentsSet);

        Set<Node> nodes = new HashSet<>();
        Set<Edge> edges = new HashSet<>();
        int i = 0;
        for (Vertex v : vertices) {
            v.setNode(new Node(i++));
            nodes.add(v.getNode());
        }
        for (PairOfVertex seg : segments) {
            edges.add(new Edge(seg.contents()[0].getNode(), seg.contents()[1].getNode()));
        }

        AdjacencyList out = new AdjacencyList(nodes, edges);
        for (Node node : nodes) {
            if (vertices.get(node.getId()).getCitySize().equals(CitySize.METROPOLIS)) {
                out.setCentralNode(node);
            }
        }

        this.vertices = vertices;
        this.segments = segments;

        return out;
    }
}
