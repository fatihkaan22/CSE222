import java.util.*;

/**
 * This class implements a data structure that contains nodes each has a fixed-size array.
 */
public class LinkedArrayList<E> extends AbstractList<E> implements List<E> {
  /**
   * Current size of the list
   */
  private int size;
  /**
   * Maximum size of arrays in each node
   */
  protected static final int arrayCapacity = 5;
  private Node<E> first;
  private Node<E> last;

  /**
   * Constructs an empty list with size of {@link #arrayCapacity}
   */
  public LinkedArrayList() {
    this.size = 0;
  }

  private static class Node<E> {
    private Array<E> data;
    private Node<E> next;
    private Node<E> prev;

    public Node(Node<E> prev, Array<E> data, Node<E> next) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }

    public boolean add(int index, E e) {
      return data.add(index, e);
    }

  }

  /**
   * Inserts the specified element at the specified postion in this list
   *
   * @param index index at which the specified element is to be inserted
   * @param e     element to be inserted
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public void add(int index, E e) {
    this.rangeCheckForAdd(index);
    if (first == null) {
      addFirst(new Array<>(e));
    } else {
      Node<E> node = getCorrespondingNode(index);
      int nodeIndex = getCorrespondingIndex(index);
      if (!node.add(nodeIndex, e)) {
        // if array is full
        if (nodeIndex == arrayCapacity) { // simply add new node with element
          linkAfter(new Array<>(e), node);
        } else {
          // add element by shifting rest of the array to new node
          Array<E> newData = node.data.subList(nodeIndex);
          linkAfter(newData, node);
          node.data.removeAfter(nodeIndex);
          node.add(nodeIndex, e);
        }
      }
    }
    size++;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public E get(int index) {
    Objects.checkIndex(index, this.size);
    Node<E> n = first;
    while (index > n.data.size - 1) {
      index -= n.data.size;
      n = n.next;
    }
    return n.data.get(index);
  }

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @param index   index of the element to replace
   * @param element element to be stored at the specified position
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  @Override
  public E set(int index, E element) {
    Objects.checkIndex(index, this.size);
    Node<E> n = first;
    while (index > n.data.size - 1) {
      index -= n.data.size;
      n = n.next;
    }
    E oldValue = n.data.get(index);
    n.data.set(index, element);
    return oldValue;
  }

  /**
   * Removes the element at the specified position in this list.
   * Shifts the elements in the array if necessary.
   * Deletes the node if there is no element in the array.
   * Returns the element that was removed from the list.
   *
   * @param index the index of the element to be removed
   * @return the element previously at the specified position
   */
  @Override
  public E remove(int index) {
    Objects.checkIndex(index, this.size);
    Node<E> node = getCorrespondingNode(index);
    int nodeIndex = getCorrespondingIndex(index);
    if (nodeIndex == node.data.size) {
      nodeIndex = 0;
      node = node.next;
    }
    size--;
    E e = node.data.remove(nodeIndex);

    if (node.data.size == 0) { //if all elements of the array is removed, then delete node
      unlink(node);
    }
    return e;
  }


  /**
   * Returns the number of elements in this list.
   *
   * @return the number of elements in this list
   */
  @Override
  public int size() {
    return size;
  }


  /**
   * Creates new node with the specified data, and adds after specified node
   *
   * @param data new node will be created with this data
   * @param succ data will be added after this node
   */
  private void linkAfter(Array<E> data, Node<E> succ) {
    Node<E> pred = succ.next;
    Node<E> newNode = new Node<>(succ, data, pred);
    succ.next = newNode;
    if (pred == null) {
      this.last = newNode;
    } else {
      pred.prev = newNode;
    }
  }

  /**
   * Deletes specified node
   *
   * @param x node to be deleted
   */
  private void unlink(Node<E> x) {
    Node<E> next = x.next;
    Node<E> prev = x.prev;
    if (prev == null) {
      this.first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null) {
      this.last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.data = null;
  }

  /**
   * Adds specified node at the beginning of the list
   *
   * @param data node constructed with specified data
   */
  private void addFirst(Array<E> data) {
    Node<E> f = this.first;
    Node<E> newNode = new Node<>(null, data, f);
    this.first = newNode;
    if (f == null) {
      this.last = newNode;
    } else {
      f.prev = newNode;
    }
  }

  /**
   * Returns the corresponding node with specified index
   *
   * @param index index of the element in the list
   * @return corresponding node
   */
  private Node<E> getCorrespondingNode(int index) {
    Node<E> n = first;
    while (index > n.data.size) {
      index -= n.data.size;
      n = n.next;
    }
    return n;
  }

  /**
   * Returns the corresponding index of node
   *
   * @param index index of the element in the list
   * @return corresponding index
   */
  private int getCorrespondingIndex(int index) {
    Node<E> n = first;
    while (n.data.size < index) {
      index -= n.data.size;
      n = n.next;
    }
    return index;
  }

  /**
   * Checks if adding is legal
   *
   * @param index index to be attempted to add
   * @throws IndexOutOfBoundsException if index not in range
   */
  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
    }
  }

  /**
   * Returns info of index and size
   *
   * @param index index trying to reach
   * @return info of index and size
   */
  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + this.size;
  }

  /**
   * Returns an iterator
   *
   * @return Iterator
   */
  @Override
  public Iterator<E> iterator() {
    return new LinkedArrayList.Itr();
  }

  /**
   * Returns a list iterator
   *
   * @return ListIterator
   */
  @Override
  public ListIterator<E> listIterator() {
    return this.listIterator(0);
  }

  /**
   * Returns a list iterator starting from specified position
   *
   * @param index portion to start
   * @return list iterator
   */
  @Override
  public ListIterator<E> listIterator(int index) {
    this.rangeCheckForAdd(index);
    return new LinkedArrayList.ListItr(index);
  }

  public class Itr implements Iterator<E> {
    int cursor = 0;
    int lastRet = -1;
    int expectedModCount;

    /**
     * @return true if there is next element, false otherwise
     */
    @Override
    public boolean hasNext() {
      return this.cursor != LinkedArrayList.this.size();
    }

    @Override
    public E next() {
      try {
        int i = this.cursor;
        E next = LinkedArrayList.this.get(i);
        this.lastRet = i;
        this.cursor = i + 1;
        return next;
      } catch (IndexOutOfBoundsException var3) {
        throw new NoSuchElementException();
      }
    }

    @Override
    public void remove() {
      if (this.lastRet < 0) {
        throw new IllegalStateException();
      } else {
        try {
          LinkedArrayList.this.remove(this.lastRet);
          if (this.lastRet < this.cursor) {
            --this.cursor;
          }

          this.lastRet = -1;
          this.expectedModCount = LinkedArrayList.this.modCount;
        } catch (IndexOutOfBoundsException var2) {
          throw new ConcurrentModificationException();
        }
      }
    }

  }

  public class ListItr extends LinkedArrayList<E>.Itr implements ListIterator<E> {

    ListItr(int index) {
      super();
      this.cursor = index;
    }

    /**
     * @return true if there is previous element, false otherwise
     */
    @Override
    public boolean hasPrevious() {
      return this.cursor != 0;
    }


    /**
     * @return the element and move backwards in the list
     */
    @Override
    public E previous() {
      try {
        int i = this.cursor - 1;
        E previous = LinkedArrayList.this.get(i);
        this.lastRet = this.cursor = i;
        return previous;
      } catch (IndexOutOfBoundsException var3) {
        throw new NoSuchElementException();
      }
    }

    /**
     * @return next index
     */
    @Override
    public int nextIndex() {
      return this.cursor;
    }

    /**
     * @return previous index
     */
    @Override
    public int previousIndex() {
      return this.cursor - 1;
    }

    /**
     * @param e replaced the last element returned by specified element
     */
    @Override
    public void set(E e) {
      if (this.lastRet < 0) {
        throw new IllegalStateException();
      } else {

        try {
          LinkedArrayList.this.set(this.lastRet, e);
          this.expectedModCount = LinkedArrayList.this.modCount;
        } catch (IndexOutOfBoundsException var3) {
          throw new ConcurrentModificationException();
        }
      }
    }

    /**
     * Adds specified element to current position of the iterator
     *
     * @param e Element to be added
     */
    @Override
    public void add(E e) {
      try {
        int i = this.cursor;
        LinkedArrayList.this.add(i, e);
        this.lastRet = -1;
        this.cursor = i + 1;
        this.expectedModCount = LinkedArrayList.this.modCount;
      } catch (IndexOutOfBoundsException var3) {
        throw new ConcurrentModificationException();
      }
    }
  }


//  /**
//   * Prints list with null positions, and separately for each node. Used for debugging purposes.
//   */
//  // prints list (for debugging purposes)
//  public void display() {
//    Node<E> n = first;
//    System.out.print(size);
//    while (n != null) {
//      System.out.print(Arrays.toString(n.data.arr) + n.data.size + "->");
//      n = n.next;
//    }
//  }


}
