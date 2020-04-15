import java.util.AbstractList;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractList<E> {
  private Node<E> first;
  private Node<E> last;
  private int size;

  /**
   * Checks if size is 0
   *
   * @throws NoSuchElementException if stack is empty
   */
  public void checkSize() {
    if (size == 0) throw new NoSuchElementException("stack is empty");
  }

  protected static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }

    /**
     * Changes item
     *
     * @param item item to be changed
     */
    public void setItem(E item) {
      this.item = item;
    }

  }

  /**
   * Adds specified node
   *
   * @param node node to add
   */
  public void addNode(Node<E> node) {
    addNodeFirst(node);
  }

  /**
   * Adds node to first
   *
   * @param node node to add
   */
  public void addNodeFirst(Node<E> node) {
    if (first == null) {
      node.next = node.prev = null;
      first = last = node;
    } else {
      node.next = first;
      first.prev = node;
      node.prev = null;
      first = node;
    }
    size++;
  }

  /**
   * Adds specified node to last
   *
   * @param node node to add
   */
  public void addNodeLast(Node<E> node) {
    if (last == null) {
      node.next = node.prev = null;
      first = last = node;
    } else {
      node.next = null;
      node.prev = last;
      last.next = node;
      last = node;
    }
    size++;
  }


  /**
   * Adds the element to first
   *
   * @param e element to add
   */
  public void addFirst(E e) {
    addNodeFirst(new Node<>(null, e, null));
  }

  /**
   * Adds element to last
   *
   * @param e element to add
   */
  public void addLast(E e) {
    addNodeLast(new Node<>(null, e, null));
  }

  /**
   * Removes first node
   *
   * @return Removed node
   */
  public Node<E> removeFirstNode() {
    if (first == null) {
      return null;
    }
    Node<E> n = first;
    first = first.next;
    if (first != null)
      first.prev = null;
    else
      last = null;
    this.size--;
    return n;
  }

  /**
   * Removes last node
   *
   * @return removed node
   */
  public Node<E> removeLastNode() {
    if (last == null) {
      return null;
    }
    Node<E> n = last;
    last = last.prev;
    if (last != null)
      last.next = null;
    else
      first = null;

    this.size--;
    return n;
  }

  /**
   * Removes first node
   *
   * @param n removed node
   */
  public void removeNode(Node<E> n) {
    if (n == first) {
      removeFirstNode();
    } else if (n == last) {
      removeLastNode();
    } else {
      n.prev.next = n.next;
      n.next.prev = n.prev;
      size--;
    }
  }


  /**
   * @return fist node
   */
  public Node<E> getNode() {
    return getFirstNode();
  }

  public Node<E> getNode(int index) {
    Node<E> n = first;
    for (int i = 0; i < index; i++)
      n = n.next;

    return n;
  }

  /**
   * @return first node
   */
  public Node<E> getFirstNode() {
    return this.first;
  }

  /**
   * @return last node
   */
  public Node<E> getLastNode() {
    return this.last;
  }

  /**
   * Check index if out of bounds
   *
   * @param index to be checked
   * @throws IllegalArgumentException if out of bounds
   */
  private void rangeCheck(int index) {
    if (index < 0 || index >= size) throw new IllegalArgumentException();
  }

  /**
   * Return element corresponding index
   *
   * @param i index
   * @return element
   */
  @Override
  public E get(int i) {
    rangeCheck(i);
    if (i == size)
      return last.item;
    Node<E> n = first;
    for (int j = 0; j < i; j++) {
      n = n.next;
    }
    return n.item;
  }

  /**
   * Returns size
   *
   * @return size
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Checks if list is empty
   *
   * @return true if empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }
}
