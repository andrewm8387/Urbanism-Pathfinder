package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer implements Renderer {

    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawSegments(aMesh, canvas);
        drawVertices(aMesh, canvas);
    }

    private void drawVertices(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Vertex vertex: aMesh.getVerticesList()){
            drawAVertex(vertex, aMesh, canvas);
        }
    }

    private void drawAVertex(Vertex v, Mesh aMesh, Graphics2D canvas) {
        if ("METROPOLIS".equals(v.getProperties(0).getValue())) {
            canvas.setColor(Color.CYAN);
            canvas.fillOval((int) v.getX() - 10, (int) v.getY() - 10, 20, 20);
        } else if ("CITY".equals(v.getProperties(0).getValue())) {
            canvas.setColor(Color.RED);
            canvas.fillOval((int) v.getX() - 8, (int) v.getY() - 8, 16, 16);
        } else if ("TOWN".equals(v.getProperties(0).getValue())) {
            canvas.setColor(Color.BLUE);
            canvas.fillOval((int) v.getX() - 4, (int) v.getY() - 4, 8, 8);
        } else if ("HAMLET".equals(v.getProperties(0).getValue())) {
            canvas.setColor(Color.GREEN);
            canvas.fillOval((int) v.getX() - 2, (int) v.getY() - 2, 4, 4);
        } else {
            canvas.setColor(Color.BLACK);
            canvas.fillOval((int) v.getX() - 1, (int) v.getY() - 1, 2, 2);
        }
    }

    private void drawSegments(Mesh aMesh, Graphics2D canvas) {
        List<Vertex> vertices = aMesh.getVerticesList();
        for (Vertex vertex : vertices) {
            drawASegment(vertex, vertices.get(Integer.parseInt(vertex.getProperties(1).getValue())), canvas);
        }
    }

    private void drawASegment(Vertex v1, Vertex v2, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        canvas.setStroke(new BasicStroke(1f));
        canvas.draw(new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY()));
    }
}
