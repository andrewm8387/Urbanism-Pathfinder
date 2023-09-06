  - Author: Sébastien Mosser (generator and visualizer)
  - Author: Andrew McLaren (pathfinder and cities update)


## How to install?

```
mvn install
```

It creates three jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular

```
java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh -c 100
```
`-c` controls the number of cities on the mesh which is only for the irregular mesh.
Results in error sometimes due to the random mesh but so rerun until it is fixed.

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing a mesh, (regular or debug mode)

```
java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`.



### Find a path between two points
Stores a graph as a adjacency list in and can find the shortest path given an unweighted graph and directed graph using breadth first search.
You can extend the graph to include weighted edges and use Dijkstra's algorithm to find the shortest path.
