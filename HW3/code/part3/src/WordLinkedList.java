
import java.util.ArrayList;

/**
 * LinkedList structure to store a word, with its crosses
 */
public class WordLinkedList {
  private Node first;
  private Node last;
  private final int size;

  public WordLinkedList(String word) {
    link(word);
    size = word.length();
  }

  class Node {
    private Character character;
    private Node next;
    private Node prev;
    private Node crossNode;

    private Node(Node prev, Character character, Node next) {
      this.character = character;
      this.next = next;
      this.prev = prev;
      this.crossNode = null;
    }

    /**
     * Adds specified cross node to this node
     *
     * @param n Cross node to be added
     */
    public void addCross(Node n) {
      crossNode = n;
    }

    /**
     * Checks if there is a cross node
     *
     * @return true if has a cross node
     */
    public boolean hasCross() {
      return crossNode != null;
    }

    /**
     * Returns the {@link WordLinkedList} which has this node
     *
     * @return word of this node
     */
    public WordLinkedList getWordRef() {
      return WordLinkedList.this;
    }

    /**
     * Returns if there is a cross node of this node
     *
     * @return if exist cross node, null otherwise
     */
    public Node getCrossNode() {
      return crossNode;
    }


    /**
     * Replaces cross node with specified value
     *
     * @param crossNode cross node to be refaced
     */
    public void setCrossNode(Node crossNode) {
      this.crossNode = crossNode;
    }
  }

  /**
   * adds word as linked list
   *
   * @param word word to be linked
   */
  private void link(String word) {
    for (Character c : word.toCharArray()) {
      linkLast(c);
    }
  }

  /**
   * Adds specified character at the end of the list
   *
   * @param c character to be added
   */
  private void linkLast(char c) {
    Node l = this.last;
    Node newNode = new Node(l, c, null);
    this.last = newNode;
    if (l == null) {
      this.first = newNode;
    } else {
      l.next = newNode;
    }
  }

  /**
   * Prints word and its crosswords with indexes
   */
  public void print() {
    System.out.print(this + ": {");
    ArrayList<String> words = getCrossWords();
    ArrayList<Integer> crossIndexes = getCrossIndexes();
    for (int i = 0; i < words.size(); i++) {
      System.out.print(" " + crossIndexes.get(i) + ":" + words.get(i));
    }
    System.out.println(" }");
  }

  /**
   * Returns list of cross indexes of word
   *
   * @return list of cross indexes
   */
  private ArrayList<Integer> getCrossIndexes() {
    Node n = first;
    ArrayList<Integer> indexes = new ArrayList<>();
    int i = 0;
    while (n != null) {
      if (n.hasCross()) {
        indexes.add(i);
      }
      i++;
      n = n.next;
    }
    return indexes;
  }

  /**
   * Returns list of characters which has cross nodes
   *
   * @return list of character nodes
   */
  protected ArrayList<Node> getCrossChars() {
    ArrayList<Node> crossChars = new ArrayList<>();
    Node n = first;
    while (n != null) {
      if (n.hasCross())
        crossChars.add(n.crossNode);
      n = n.next;
    }
    return crossChars;
  }

  /**
   * Returns corresponding node of specified index
   *
   * @param index index to be returned
   * @return corresponding node
   */
  private Node getNode(int index) {
    Node tmp = first;
    for (int i = 0; i < index; i++) {
      tmp = tmp.next;
    }
    return tmp;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("size: " + size + ", index: " + index);
  }

  private void checkIndex(WordLinkedList word, int index) {
    if (index < 0 || index >= word.size)
      throw new IndexOutOfBoundsException("size: " + word.size + ", index: " + index);
  }

  /**
   * Adds cross to specified indexes
   *
   * @param word      word to be crossed to this word
   * @param thisIndex index of this word to be crossed
   * @param wordIndex index of specified word to be crossed
   * @throws IndexOutOfBoundsException if one of the indexes is out of bounds
   * @throws IllegalArgumentException  if corresponding character of indexes are different
   */
  public void addCross(WordLinkedList word, int thisIndex, int wordIndex) {
    checkIndex(thisIndex);
    checkIndex(word, wordIndex);
    checkCrossesAreSameChar(word, thisIndex, wordIndex);

    Node thisNode = getNode(thisIndex);
    thisNode.addCross(word.getNode(wordIndex));
    word.getNode(wordIndex).addCross(thisNode);
  }

  /**
   * @param index index of node
   * @return
   */
  private Node get(int index) {
    Node n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }
    return n;
  }

  private void checkCrossesAreSameChar(WordLinkedList word, int thisIndex, int wordIndex) {
    char c1 = get(thisIndex).character;
    char c2 = word.get(wordIndex).character;
    if (c1 != c2)
      throw new IllegalArgumentException("Characters in the indexes are not same, " +
              "can not be crossed, thisIndex: " + c1 + " wordIndex: " + c2);
  }

  /**
   * Removes cross of node with corresponding index
   *
   * @param index index to be removed
   */
  public void removeCross(int index) {
    checkIndex(index);
    Node crossNode = getNode(index).crossNode;
    getNode(index).crossNode = null;
    crossNode.crossNode = null;
  }

  /**
   * Returns all cross word of specified word as list
   *
   * @return list of words
   */
  public ArrayList<String> getCrossWords() {
    ArrayList<String> words = new ArrayList<>();
    Node n = first;
    while (n != null) {
      if (n.hasCross()) {
        words.add(n.crossNode.getWordRef().toString());
      }
      n = n.next;
    }
    return words;
  }

  @Override
  public String toString() {
    Node n = first;
    StringBuilder sb = new StringBuilder();
    while (n != null) {
      sb.append(n.character);
      n = n.next;
    }
    return sb.toString();
  }
}
