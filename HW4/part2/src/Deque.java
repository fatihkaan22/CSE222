import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<E> extends AbstractCollection<E> implements java.util.Deque<E> {
  /**
   * Actual data
   */
  private LinkedList<E> deque = new LinkedList<>();
  /**
   * Removed nodes
   */
  private LinkedList<E> removed = new LinkedList<>();

  private class Itr implements Iterator<E> {
    private LinkedList.Node<E> lastReturned;
    private LinkedList.Node<E> next;
    private int nextIndex;

    Itr() {
      this.next = deque.getFirstNode();
      this.nextIndex = 0;
    }

    /**
     * @return true if has next element after last returned
     */
    public boolean hasNext() {
      return this.nextIndex < deque.size();
    }

    /**
     * Return element and iterate to next
     *
     * @return element
     */
    public E next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      } else {
        this.lastReturned = this.next;
        this.next = this.next.next;
        ++this.nextIndex;
        return this.lastReturned.item;
      }
    }


    /**
     * Remove node if last returned node is either first or last node
     */
    public void remove() {
      if (this.lastReturned == null) {
        throw new IllegalStateException();
      } else if (this.lastReturned == deque.getLastNode()) {
        Deque.this.removeLast();
      } else if (this.lastReturned == deque.getFirstNode()) {
//        LinkedList.Node<E> lastNext = this.lastReturned.next;
        Deque.this.removeFirst();
        //TODO: why
//        if (this.next == this.lastReturned) {
//          this.next = lastNext;
//        } else {
//          --this.nextIndex;
//        }
        this.lastReturned = null;
      } else {
        throw new UnsupportedOperationException("You can only remove first or last element of the deque");
      }
    }
  }

  private class DItr implements Iterator<E> {
    private LinkedList.Node<E> lastReturned;
    private LinkedList.Node<E> next;
    private int nextIndex;

    DItr() {
      this.next = deque.getLastNode();
      this.nextIndex = deque.size() - 1;
    }


    /**
     * @return true if has next element after last returned
     */
    public boolean hasNext() {
      return (this.nextIndex != -1);
    }

    /**
     * Return element and iterate to next
     *
     * @return element
     */
    public E next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      } else {
        this.lastReturned = this.next;
        this.next = this.next.prev;
        --this.nextIndex;
        return this.lastReturned.item;
      }
    }

    /**
     * Remove node if last returned node is either first or last node
     */
    public void remove() {
      if (this.lastReturned == null) {
        throw new IllegalStateException();
      } else if (this.lastReturned == deque.getLastNode()) {
        Deque.this.removeLast();
      } else if (this.lastReturned == deque.getFirstNode()) {
//        LinkedList.Node<E> lastNext = this.lastReturned.next;
        Deque.this.removeFirst();
        //TODO: why
//        if (this.next == this.lastReturned) {
//          this.next = lastNext;
//        } else {
//          --this.nextIndex;
//        }
        this.lastReturned = null;
      } else {
        throw new UnsupportedOperationException("You can only remove first or last element of the deque");
      }
    }
  }

  /**
   * Returns an iterator over the elements in this deque in proper sequence.
   *
   * @return iterator
   */
  @Override
  public Iterator<E> iterator() {
    return new Deque<E>.Itr();
  }

  /**
   * Returns an iterator over the elements in this deque in reverse sequential order.
   *
   * @return iterator
   */
  @Override
  public Iterator<E> descendingIterator() {
    return new Deque<E>.DItr();
  }

  /**
   * Inserts the specified element at the front of this deque.
   *
   * @param e element to add
   */
  @Override
  public void addFirst(E e) {
    if (removed.isEmpty()) {
      deque.addFirst(e);
    } else {
      LinkedList.Node<E> n = removed.getNode();
      n.setItem(e);
      deque.addNodeFirst(n);
      removed.removeFirstNode();
    }
  }

  /**
   * Inserts the specified element at the end of this deque
   *
   * @param e element to add
   */
  @Override
  public void addLast(E e) {
    if (removed.isEmpty()) {
      deque.addLast(e);
    } else {
      LinkedList.Node<E> n = removed.getNode();
      n.setItem(e);
      deque.addNodeLast(n);
      removed.removeFirstNode();
    }
  }

  /**
   * Inserts the specified element at the front of this deque
   *
   * @param e element to offer
   * @return true
   */
  @Override
  public boolean offerFirst(E e) {
    addFirst(e);
    return true;
  }

  /**
   * Inserts the specified element at the end of this deque
   *
   * @param e element to offer
   * @return true
   */
  @Override
  public boolean offerLast(E e) {
    addLast(e);
    return true;
  }

  /**
   * Retrieves and removes the first element
   *
   * @return removed element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E removeFirst() {
    deque.checkSize();
    return pollFirst();
  }

  /**
   * Retrieves and removes the last element
   *
   * @return removed element
   */
  @Override
  public E removeLast() {
    deque.checkSize();
    return pollLast();
  }

  /**
   * Retrieves and removes the first element
   *
   * @return null if empty, otherwise removed item
   */
  @Override
  public E pollFirst() {
    if (deque.size() == 0) return null;
    LinkedList.Node<E> n = deque.removeFirstNode();
    removed.addNode(n);
    return n.item;
  }

  /**
   * Retrieves and removes the last element
   *
   * @return null if empty, otherwise removed item
   */
  @Override
  public E pollLast() {
    if (deque.size() == 0) return null;
    LinkedList.Node<E> n = deque.removeLastNode();
    removed.addNode(n);
    return n.item;
  }

  /**
   * Retrieves first element
   *
   * @return element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E getFirst() {
    deque.checkSize();
    return peekFirst();
  }

  /**
   * Retrieves last element
   *
   * @return element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E getLast() {
    deque.checkSize();
    return peekLast();
  }

  /**
   * Retrieves first element
   *
   * @return null if empty, otherwise element
   */
  @Override
  public E peekFirst() {
    if (deque.size() == 0) return null;
    return deque.getFirstNode().item;
  }

  /**
   * Retrieves last element
   *
   * @return null if empty, otherwise element
   */
  @Override
  public E peekLast() {
    if (deque.size() == 0) return null;
    return deque.getLastNode().item;
  }

  /**
   * Removes the first occurrence of the specified element
   *
   * @param o element to be removed
   * @return true if removed, false otherwise
   */
  @Override
  public boolean removeFirstOccurrence(Object o) {
    boolean result = false;
    LinkedList.Node<E> n = deque.getFirstNode();
    while (n != null) {
      if (n.item.equals(o)) {
        deque.removeNode(n);
        removed.addNode(n);
        result = true;
        break;
      }
      n = n.next;
    }
    return result;
  }

  /**
   * Removes the last occurrence of the specified element
   *
   * @param o element to be removed
   * @return true if removed, false otherwise
   */
  @Override
  public boolean removeLastOccurrence(Object o) {
    LinkedList.Node<E> n = deque.getLastNode();
    while (n != null) {
      if (n.item.equals(o)) {
        deque.remove(o);
        removed.addNode(n);
        return true;
      }
      n = n.prev;
    }
    return false;
  }

  /**
   * Inserts the specified element at the end of this deque
   *
   * @param e element to add
   * @return true
   */
  @Override
  public boolean offer(E e) {
    return offerLast(e);
  }


  /**
   * Retrieves and removes the first element
   *
   * @return removed element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E remove() {
    return removeFirst();
  }

  /**
   * Retrieves and removes the first element
   *
   * @return removed element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E poll() {
    return pollFirst();
  }

  /**
   * Retrieves first element
   *
   * @return element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E element() {
    return getFirst();
  }


  /**
   * Retrieves first element
   *
   * @return null if empty, otherwise element
   */
  @Override
  public E peek() {
    return peekFirst();
  }

  /**
   * Inserts the specified element at the front of this deque.
   *
   * @param e element to add
   */
  @Override
  public void push(E e) {
    addFirst(e);
  }

  /**
   * Retrieves and removes the first element
   *
   * @return removed element
   * @throws NoSuchElementException if deque is empty
   */
  @Override
  public E pop() {
    return removeFirst();
  }

  /**
   * Returns the size of deque
   *
   * @return size of deque
   */
  @Override
  public int size() {
    return deque.size();
  }

}
