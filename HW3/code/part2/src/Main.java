
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

  public static void main(String[] args) {

    String filename = "file.txt";
    Logger logger = new Logger();


    logger.init();

    System.out.println("**LinkedList and iterator is used**");
    TextEditorIterator iterLL = new TextEditorIterator(new LinkedList<>());
    iterLL.read(filename);
    logger.log("read method, LinkedList with Iterator");
    System.out.println("String found at: " + iterLL.find("libero(f)"));
    logger.log("find method, LinkedList with Iterator");
    System.out.println(iterLL.replace('o', '_') + " replaced chars.");
    logger.log("replace method, LinkedList with Iterator");
    iterLL.add(32611, "******");
    System.out.println("String added specified position.");
    logger.log("add method, LinkedList with Iterator");
//    iterLL.print();

    System.out.println("**ArrayList and iterator is used**");
    TextEditorIterator iterAL = new TextEditorIterator(new ArrayList<>());
    iterAL.read(filename);
    logger.log("read method, ArrayList with Iterator");
    System.out.println("String found at: " + iterAL.find("libero(f)"));
    logger.log("find method, ArrayList with Iterator");
    System.out.println(iterAL.replace('o', '_') + " replaced chars.");
    logger.log("replace method, ArrayList with Iterator");
    iterAL.add(32611, "******");
    System.out.println("String added specified position.");
    logger.log("add method, ArrayList with Iterator");
//    iterAL.print();


    System.out.println("**LinkedList and loop is used**");
    TextEditorLoop loopLL = new TextEditorLoop(new LinkedList<>());
    loopLL.read(filename);
    logger.log("read method, LinkedList with loop");
    System.out.println("String found at: " + loopLL.find("libero(f)"));
    logger.log("find method, LinkedList with loop");
    System.out.println(loopLL.replace('o', '_') + " replaced chars.");
    logger.log("replace method, LinkedList with loop");
    loopLL.add(32611, "******");
    System.out.println("String added specified position.");
    logger.log("add method, LinkedList with loop");
//    loopLL.print();

    System.out.println("**ArrayList and loop is used**");
    TextEditorLoop loopAL = new TextEditorLoop(new ArrayList<>());
    loopAL.read(filename);
    logger.log("read method, ArrayList with loop");
    System.out.println("String found at: " + loopAL.find("libero(f)"));
    logger.log("find method, ArrayList with loop");
    System.out.println(loopAL.replace('o', '_') + " replaced chars.");
    logger.log("replace method, ArrayList with loop");
    loopAL.add(32611, "******");
    System.out.println("String added specified position.");
    logger.log("add method, ArrayList with loop");
//    loopAL.print();

    logger.writeToFile("logFile.log");

    System.out.println("Test Cases");
    iterAL.add(0, "deneme");
    loopAL.add(0, "deneme");
    System.out.println(iterAL.replace('8', 'a'));
    System.out.println(iterAL.find("asdf"));
    System.out.println(loopAL.replace('8', 'a'));
    System.out.println(loopAL.find("asdf"));
    iterAL.add(-1, "x");
    iterAL.add(999999, "x");

  }
}
