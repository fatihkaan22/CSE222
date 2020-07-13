public class Main {
  public static void main(String[] args) {

    Maze maze = new Maze("maze.txt");
    maze.draw();
    maze.printVertices();
    maze.printEdges();
    maze.printGraph();
    maze.solve();

//    System.out.println("T1");
//    Maze m3 = new Maze("maze3.txt");
//    m3.draw();
//    m3.solve();
//    System.out.println("T2");
//    Maze m2 = new Maze("maze2.txt");


  }


}
