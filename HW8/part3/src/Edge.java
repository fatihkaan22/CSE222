public class Edge {
  private final int dest;
  private final int source;
  private final double weight;

  public Edge(int source, int dest, double weight) {
    this.source = source;
    this.dest = dest;
    this.weight = weight;
  }

  public boolean equals(Edge e) {
    return (this.source == e.source && this.dest == e.dest);
  }

  public int getDest() {
    return this.dest;
  }

  public int getSource() {
    return this.source;
  }

  public double getWeight() {
    return this.weight;
  }

  public int hashCode() {
    return Integer.hashCode(source) + Integer.hashCode(dest);
  }

  @Override
  public String toString() {
    return "Edge{" +
            "dest=" + dest +
            ", source=" + source +
            ", weight=" + weight +
            '}' + '\n';
  }
}