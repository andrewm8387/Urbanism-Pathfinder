package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import Resources.Graph.Node;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.CitySize;

import java.util.Objects;

import static ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.CitySize.*;

public class Vertex implements Cropable<Vertex> {

    private static final int PRECISION = 2;
    private final int x, y;
    private CitySize citySize;
    private Node node;
    private Vertex nextVertex = this;

    public void setNextVertex(Vertex nextVertex) {
        this.nextVertex = nextVertex;
    }

    public Vertex getNextVertex() {
        return nextVertex;
    }

    public Vertex(float x, float y) {
        this.x = convert(x);
        this.y = convert(y);
    }

    public float x() {
        return this.x / (float) Math.pow(10, PRECISION);
    }

    public float y() {
        return this.y / (float) Math.pow(10, PRECISION);
    }

    @Override
    public Vertex crop(float maxX, float maxY) {
        float newX = Math.max(0.0f, Math.min(this.x(), maxX));
        float newY = Math.max(0.0f, Math.min(this.y(), maxY));
        return new Vertex(newX, newY);
    }

    private int convert(float x) {
        return (int) Math.round(x*Math.pow(10, PRECISION));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return x == vertex.x && y == vertex.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x() +
                ", " + y() +
                ')';
    }

    public CitySize getCitySize() {
        return citySize;
    }

    public void setCitySize(CitySize citySize) {
        this.citySize = citySize;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

}
