import java.util.*;

public class ListGraph {
  private final List<Edge>[] edges;
  /**
   * Number of vertices
   */
  private final int numV;

  public ListGraph(int numV) {
    this.numV = numV;
    edges = new List[numV];
    for (int i = 0; i < numV; i++) {
      edges[i] = new LinkedList<>();
    }
  }

  /**
   * Inserts specified edge to graph
   *
   * @param edge edge to be added
   */
  public void insert(Edge edge) {
    edges[edge.getSource()].add(edge);
    edges[edge.getDest()].add(new Edge(edge.getDest(),
            edge.getSource(),
            edge.getWeight()));
  }

  public Iterator<Edge> edgeIterator(int source) {
    return edges[source].iterator();
  }

  /**
   * Returns edge with specified source and destination vertices.
   *
   * @param source source vertex
   * @param dest   destination vertex
   * @return found edge or edge with positive infinity value, in case graph doesn't have the edge
   */
  public Edge getEdge(int source, int dest) {
    Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
    for (Edge edge : edges[source]) {
      if (edge.equals(target))
        return edge;
    }
    return target;
  }

  public int getNumV() {
    return numV;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    int end = edges.length;
    for (int i = 0; i < end; i++) {
      sb.append("\n" + i + ": ");
      for (Edge e : edges[i]) {
        sb.append("(" + e.getDest() + ", " + e.getWeight() + ") ");
      }
    }
    return sb.toString();
  }

  /**
   * Dijkstras algorithm to find shortest path
   *
   * @param start start point of search
   * @param pred  pred array
   * @param dist  end point of search
   */
  public void dijkstrasAlgorithm(
          int start,
          int[] pred,
          double[] dist) {
    int numV = getNumV();
    HashSet<Integer> vMinusS = new HashSet<>(numV);
    for (int i = 0; i < numV; i++) {
      if (i != start)
        vMinusS.add(i);
    }
    for (int v : vMinusS) {
      pred[v] = start;
      dist[v] = getEdge(start, v).getWeight();
    }
    while (vMinusS.size() != 0) {
      double minDist = Double.POSITIVE_INFINITY;
      int u = -1;
      for (int v : vMinusS) {
        if (dist[v] < minDist) {
          minDist = dist[v];
          u = v;
        }
      }
      vMinusS.remove(u);
      Iterator<Edge> edgeIter = edgeIterator(u);
      while (edgeIter.hasNext()) {
        Edge edge = edgeIter.next();
        int v = edge.getDest();
        if (vMinusS.contains(v)) {
          double weight = edge.getWeight();
          if (dist[u] + weight < dist[v]) {
            dist[v] = dist[u] + weight;
            pred[v] = u;
          }
        }
      }
    }
  }
}