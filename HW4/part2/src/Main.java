import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    System.out.println("Test Cases");
    System.out.println("T01");
    deque.addFirst(2);
    deque.push(1);

    deque.offer(3);
    deque.addLast(4);

    System.out.println(deque);

    System.out.println("T02");
    Iterator<Integer> iter = deque.iterator();
    while (iter.hasNext())
      System.out.print(iter.next() + " ");
    System.out.println();

    System.out.println("T03");
    Iterator<Integer> diter = deque.descendingIterator();
    while (diter.hasNext())
      System.out.print(diter.next() + " ");
    System.out.println();

    System.out.println("T04");
    deque.removeFirst();
    System.out.println(deque);

    System.out.println("T05");
    deque.removeLast();
    System.out.println(deque);

    System.out.println("T06");
    System.out.println(deque.peekFirst());
    System.out.println(deque);

    System.out.println("T07");
    deque.poll();
    System.out.println(deque);

    System.out.println("T08");
    deque.pop();
    System.out.println(deque);

    System.out.println("T09");
    System.out.println(deque.poll());
    System.out.println(deque);

    System.out.println("T10");
    deque.pop();
    System.out.println(deque);
  }
}
