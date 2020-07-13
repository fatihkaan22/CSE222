public class Vertex {
  private final int x;
  private final int y;
  /**
   * to create unique id's for verticies
   */
  private static int idCounter = 0;
  private final int id;

  public Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.id = idCounter++;
  }

  public String getKey() {
    return getKey(this.x, this.y);
  }

  /**
   * Creates unique key to get vertices from hashmap
   *
   * @param x x value
   * @param y y value
   * @return unique key
   */
  public static String getKey(int x, int y) {
    return (x + " " + y);
  }

  @Override
  public String toString() {
    return "Vertex{" +
            "x=" + x +
            ", y=" + y +
            ", id=" + id +
            '}' + '\n';
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getId() {
    return id;
  }
}
