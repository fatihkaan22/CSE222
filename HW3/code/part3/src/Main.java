
public class Main {

  public static void main(String[] args) {


    WordLinkedList puzzles = new WordLinkedList("PUZZLES");
    WordLinkedList fun = new WordLinkedList("FUN");
    WordLinkedList crossword = new WordLinkedList("CROSSWORD");
    WordLinkedList are = new WordLinkedList("ARE");

    puzzles.addCross(fun, 1, 1);
    crossword.addCross(puzzles, 4, 6);
    crossword.addCross(are, 1, 1);

    CrossWordPuzzle puzzle = new CrossWordPuzzle();


    System.out.println("**ADD TEST**");
    puzzle.addWordWithCrosses(puzzles);
    puzzle.addWordWithCrosses(crossword);
    puzzle.addWordWithCrosses(are); //unnecessary since we already add its crosses

    puzzle.print();
    System.out.println();

    System.out.println("**REMOVE TEST**");
    puzzle.removeWord(fun);
    System.out.println("FUN removed");

    puzzle.print();

    System.out.println("**SECOND PUZZLE**");
    WordLinkedList brahms = new WordLinkedList("BRAHMS");
    WordLinkedList handel = new WordLinkedList("HANDEL");
    WordLinkedList beethoven = new WordLinkedList("BEETHOVEN");
    WordLinkedList strauss = new WordLinkedList("STRAUSS");
    WordLinkedList wagner = new WordLinkedList("WAGNER");
    WordLinkedList liszt = new WordLinkedList("LISZT");
    WordLinkedList bach = new WordLinkedList("BACH");
    WordLinkedList chopin = new WordLinkedList("CHOPIN");
    WordLinkedList mozart = new WordLinkedList("MOZART");
    WordLinkedList haydn = new WordLinkedList("HAYDN");

    CrossWordPuzzle composers = new CrossWordPuzzle();

    brahms.addCross(handel, 2, 1);
    handel.addCross(beethoven,4,1);
    beethoven.addCross(strauss,3,1);
    strauss.addCross(wagner,3,1);
    strauss.addCross(liszt,5,2);
    bach.addCross(chopin,2,0);
    chopin.addCross(mozart,2,1);
    chopin.addCross(beethoven,5,8);
    mozart.addCross(haydn,3,1);

    composers.addWordWithCrosses(brahms);
    composers.addWordWithCrosses(beethoven);
    composers.addWordWithCrosses(strauss);
    composers.addWordWithCrosses(chopin);
    composers.addWordWithCrosses(haydn);

    composers.print();
  }
}
