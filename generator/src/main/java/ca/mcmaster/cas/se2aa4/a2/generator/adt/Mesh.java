package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.neighborhoud.DelaunayNeighbourhood;
import ca.mcmaster.cas.se2aa4.a2.generator.neighborhoud.Neighborhood;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Mesh implements Iterable<Polygon> {

    private Set<Polygon> polygons;
    private int width, height;
    private int numberOfCities;

    public Mesh(int width, int height, int numberOfCities) {
        this.width = width;
        this.height = height;
        this.polygons = new HashSet<>();
        this.numberOfCities = numberOfCities;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void register(Polygon p) {
        this.polygons.add(p.crop(width, height));
    }

    @Override
    public Iterator<Polygon> iterator() {
        return this.polygons.iterator();
    }

    public Set<Polygon> getPolygons() {
        return this.polygons;
    }

    public void populateNeighbours(Neighborhood neighbourhood) {
        neighbourhood.build(this.polygons);
        for(Polygon p: this) {
            for(Polygon n: neighbourhood.neighbors(p)) {
                p.registerAsNeighbour(n);
            }
        }
    }

    @Override
    public String toString() {
        return "Mesh(" +width+"x"+height+","+polygons+")";
    }
}
