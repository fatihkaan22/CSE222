
import java.util.ArrayList;

/**
 * List of words to store a puzzle
 */
public class CrossWordPuzzle {
  private ArrayList<WordLinkedList> words;

  public CrossWordPuzzle() {
    this.words = new ArrayList<>();
  }

  /**
   * Adds specified word to the list, if not in the list already
   * (This method doesn't add crosswords of this word)
   *
   * @param word word to be added
   * @return true if added, false if already in the list
   */
  public boolean addWord(WordLinkedList word) {
    boolean exist = false;
    for (WordLinkedList w : words) {
      if (word.equals(w)) {
        exist = true;
        break;
      }
    }

    if (!exist)
      words.add(word);

    return !exist;
  }

  /**
   * Removes the word from list and its crosses from other words
   *
   * @param word word to be removed
   */
  public void removeWord(WordLinkedList word) {
    for (WordLinkedList w : words) {
      for (WordLinkedList.Node c : w.getCrossChars()) {
        if (c.getCrossNode().getWordRef().equals(word)) {
          c.setCrossNode(null);
        }
      }
    }

    words.remove(word);
  }

  /**
   * Adds the word to the list, and all other crosswords of the word
   *
   * @param word word to be added
   */
  public void addWordWithCrosses(WordLinkedList word) {
    addWord(word);
    word.getCrossChars().forEach(node -> addWord(node.getWordRef()));
  }


  /**
   * Removes the word from list, and all other words which has cross reference to this word
   *
   * @param word word to be removed
   */
  public void removeWordWithCrosses(WordLinkedList word) {
    removeWord(word);
    word.getCrossChars().forEach(node -> removeWord(node.getWordRef()));
  }


  /**
   * Prints whole list with cross indexes
   */
  public void print() {
    words.forEach(WordLinkedList::print);
  }

}
