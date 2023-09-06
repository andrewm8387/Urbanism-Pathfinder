
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Cities.AddCities;
import ca.mcmaster.cas.se2aa4.a2.generator.Infrastructure.Roads.AddRoads;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.export.Exporter;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Buildable specification = SpecificationFactory.create(config);
        Mesh theMesh = specification.build();
        new AddCities().add(theMesh);
        new AddRoads().add(theMesh);
        Structs.Mesh exported = new Exporter().run(theMesh);
        new MeshFactory().write(exported, config.export(Configuration.FILENAME));
    }
}