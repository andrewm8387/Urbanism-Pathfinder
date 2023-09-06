package ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities;

import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Addable;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;

import java.util.*;

public class AddCities implements Addable {

    public void add(Mesh mesh) {
        //Pick random vertices and make them cities
        Set<Vertex> verticesSet = new HashSet<>();
        for (Polygon p : mesh.getPolygons()) {
            verticesSet.addAll(p.hullVertices());
        }
        List<Vertex> vertices = new ArrayList<>(verticesSet);

        //Pick random vertices and make them cities
        Random random = new Random();
        int numberOfCities = mesh.getNumberOfCities();

        Set<Integer> citiesIndex = new HashSet<>();

        int maxX = 0, maxY = 0;
        for (Vertex v : vertices) {
            if (v.x() > maxX) maxX = (int) v.x();
            if (v.y() > maxY) maxY = (int) v.y();
        }

        //Make sure the metropolis is in the middleish
        int lowerX = maxX / 3, lowerY = maxY / 3, upperX = 2 * lowerX, upperY = 2 * lowerY;
        Vertex vertex;
        int index;
        do {
            index = random.nextInt(vertices.size());
            vertex = vertices.get(index);
        } while (vertex.x() < lowerX || vertex.x() > upperX || vertex.y() < lowerY || vertex.y() > upperY);

        citiesIndex.add(index);
        vertex.setCitySize(CitySize.METROPOLIS); //There is just one metropolis


        for (int i = 0; i < numberOfCities; i++) {
            do {index = random.nextInt(vertices.size());} while (citiesIndex.contains(index));
            vertex = (Vertex) vertices.toArray()[index];
            vertex.setCitySize(CitySize.values()[random.nextInt(1, CitySize.values().length) - 1]);

        }
    }
}
