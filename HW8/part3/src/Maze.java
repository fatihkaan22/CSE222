import java.io.*;
import java.util.*;

public class Maze {
  /**
   * 2D boolean arraylist read from file
   */
  ArrayList<ArrayList<Boolean>> maze;
  protected HashSet<Edge> edges = new HashSet<>();
  protected HashMap<String, Vertex> vertices = new HashMap<>();
  /**
   * Visited places to avoid revisit, when discovering the map
   */
  private boolean[][] visited;
  private ListGraph graph;

  /**
   * Reads file and constructs maze, calls method to find vertex and edges and create graph.
   *
   * @param filename file to be read
   */
  public Maze(String filename) {
    this.maze = new ArrayList<>();
    readFromFile(filename);
    discover(); //find vertex and edges
    createGraph();
  }

  /**
   * Inserts found edges to graph.
   */
  private void createGraph() {
    int noVertices = vertices.keySet().size();
    this.graph = new ListGraph(noVertices);
    for (Edge e : edges)
      graph.insert(e);
  }

  /**
   * Reads binary maze from file and constructs 2d Boolean ArrayList.
   *
   * @param filename file to be read
   */
  public void readFromFile(String filename) {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(new File(filename)));
      String lineStr;
      while ((lineStr = reader.readLine()) != null) {
        ArrayList<Boolean> line = new ArrayList<>();
        for (String s : lineStr.split(""))
          if (s.equals("1"))
            line.add(true);
          else if (s.equals("0"))
            line.add(false);
          else
            throw new IllegalArgumentException("File must contain only 1's and 0's");
        maze.add(line);
      }

      this.visited = new boolean[maze.size()][maze.get(0).size()];
      for (boolean[] row : this.visited)
        Arrays.fill(row, false);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Draws positions of vertices for debugging purpose.
   */
  public void draw() {
    int[][] map = new int[maze.size()][maze.get(0).size()];
    for (int[] row : map)
      Arrays.fill(row, -1);

    for (Vertex v : vertices.values()) {
      map[v.getX()][v.getY()] = v.getId();
    }

    for (int[] ints : map) {
      for (int j = 0; j < map[0].length; j++) {
        if (ints[j] == -1)
          System.out.print(".");
        else
          System.out.print(ints[j]);
      }
      System.out.println();
    }
  }

  /**
   * Helper method that initialize recursive method form starting point.
   */
  public void discover() {
    Vertex startVertex = new Vertex(0, 0);
    visited[0][0] = true;
    vertices.put(startVertex.getKey(), startVertex);
    discover(0, 1, startVertex, 1);
    discover(1, 0, startVertex, 1);
  }

  /**
   * Finds all vertices and edges.
   *
   * @param x          current x position
   * @param y          current y position
   * @param lastVertex last found vertex to create edge
   * @param distance   distance from last found vertex
   */
  private void discover(int x, int y, Vertex lastVertex, int distance) {
    if (x >= maze.size() || y >= maze.get(0).size() || x < 0 || y < 0) //out of map
      return;
    if (maze.get(x).get(y).equals(true)) //not valid
      return;
    if (isVertex(x, y)) {
      Vertex v = getVertex(x, y);
      if (v == null) { // not found early can be added
        v = new Vertex(x, y);
        vertices.put(v.getKey(), v);
        Edge e = new Edge(lastVertex.getId(), v.getId(), distance);
        edges.add(e);
      } else if (v.getX() == lastVertex.getX() && lastVertex.getY() == y) //coming from same vertex, don't add
        return;
      else if (distance != 1) { //coming from another path to previously visited vertex also add this edge
        Edge e = new Edge(lastVertex.getId(), v.getId(), distance);
        edges.add(e);
      }
      lastVertex = v;
      distance = 0;
    }
    // visited
    if (visited[x][y])
      return;
    visited[x][y] = true;

    discover(x + 1, y, lastVertex, distance + 1);
    discover(x - 1, y, lastVertex, distance + 1);
    discover(x, y + 1, lastVertex, distance + 1);
    discover(x, y - 1, lastVertex, distance + 1);
  }

  /**
   * Returns vertex value with specified x and y
   *
   * @param x x coordinate of the vertex
   * @param y y coordinate of the vertex
   * @return found vertex, null if not found
   */
  private Vertex getVertex(int x, int y) {
    return vertices.get(Vertex.getKey(x, y));
  }

  /**
   * Checks if given coordinates is a vertex, by looking number of zeros it's surroundings
   *
   * @param x x coordinate of the entry
   * @param y y coordinate of the entry
   * @return true if given entry is vertex, false otherwise
   */
  private boolean isVertex(int x, int y) {
    int noZeroes = 0;
    if (x < maze.size() - 1 && !maze.get(x + 1).get(y)) noZeroes++;
    if (x > 0 && !maze.get(x - 1).get(y)) noZeroes++;
    if (y < maze.get(0).size() - 1 && !maze.get(x).get(y + 1)) noZeroes++;
    if (y > 0 && !maze.get(x).get(y - 1)) noZeroes++;
    return noZeroes > 2 || noZeroes == 1;
  }


  /**
   * Initializes fields and uses dijkstras algorithm to find shortest path, prints out the path.
   */
  public void solve() {
    int noVertices = vertices.keySet().size();
    int[] pred = new int[noVertices];
    double[] dist = new double[noVertices];
    graph.dijkstrasAlgorithm(0, pred, dist);

    System.out.println("**DIJKSTRAS**");
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < noVertices; i++) {
      map.put(i, pred[i]);
      System.out.println(i + " " + pred[i] + " " + dist[i]);
    }
    int destination = getVertex(maze.size() - 1, maze.get(0).size() - 1).getId();

    System.out.println("**SHORTEST PATH**");
    Stack<Integer> stack = new Stack<>();
    do {
      stack.push(destination);
      destination = map.get(destination);
      // 0 is start vertex always
    } while (destination != 0);
    stack.push(0);

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
    System.out.println();
  }

  /**
   * Prints out the graph structure
   */
  public void printGraph() {
    System.out.println("**GRAPH**");
    System.out.println(graph);
  }

  /**
   * Prints vertices
   */
  public void printVertices() {
    System.out.println("**VERTICES**");
    System.out.println(vertices.values());
  }

  /**
   * Prints edges
   */
  public void printEdges() {
    System.out.println("**EDGES**");
    System.out.println(edges);
  }

}
