import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.AddCities;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.CitySize;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Roads.AddRoads;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrbanismTest {

    private static Mesh theMesh;

    @BeforeAll
    static void setUp() {
        String[] args = new String[] {"-k", "irregular", "-h", "1080", "-w", "1920", "-p", "1000", "-s", "20", "-o", "irregular.mesh", "-c", "100"};
        Configuration config = new Configuration(args);
        Buildable specification = SpecificationFactory.create(config);
        theMesh = specification.build();
    }

    @Test
    void AddCities() {
        assert theMesh != null;
        assert theMesh.getNumberOfCities() == 100;
        new AddCities().add(theMesh);
        for (Polygon p: theMesh.getPolygons()) {
            for (Vertex v: p.hullVertices()) {
                assert v.getCitySize() != null;
            }
        }
    }

    @Test
    void AddRoads() {

        int meshSize = 0;
        for (Polygon p: theMesh.getPolygons()) {
            meshSize += p.hullVertices().size();
        }

        assert theMesh != null;
        new AddRoads().add(theMesh);
        for (Polygon p: theMesh.getPolygons()) {
            for (Vertex v: p.hullVertices()) {
                //assert cities are not pointing to themselves
                assert v.getCitySize() == CitySize.NONE || v.getCitySize() == CitySize.METROPOLIS || v.getNextVertex() != v;
                //assert cities have a path to the metropolis
                if (v.getCitySize() != CitySize.NONE) {
                    Vertex current = v;
                    int loopCount = 0;
                    while (current.getCitySize() != CitySize.METROPOLIS) {
                        assert current != current.getNextVertex();
                        current = current.getNextVertex();
                        loopCount++;
                        assert loopCount < meshSize;
                    }
                }
            }
        }
    }
}