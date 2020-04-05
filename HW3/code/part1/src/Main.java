public class Main {
  public static void main(String[] args) {

    System.out.println("Test Cases");

    System.out.println("T01");
    LinkedArrayList<Integer> lal = new LinkedArrayList<>();
    lal.add(1);
    lal.add(2);
    System.out.println(lal);
    lal.clear();

    System.out.println("T02");
    lal.add(1);
    lal.add(2);
    lal.remove(1);
    System.out.println(lal);
    lal.clear();

    System.out.println("T03");
    lal.add(1);
    lal.add(0, 2);
    System.out.println(lal);
    lal.clear();

    System.out.println("T04");
    lal.add(0);
    lal.add(2);
    lal.add(1, 1);
    System.out.println(lal);
    lal.clear();

    System.out.println("T05");
    lal.add(-1, 0);
    lal.add(100, 0);
    lal.remove(-2);
    lal.remove(100);


  }
}
