import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedArrayListTest {

  ArrayList al = new ArrayList();
  LinkedArrayList lal = new LinkedArrayList();

  @org.junit.jupiter.api.Test
  void add() {
    lal.add(0, 0);
    lal.add(3);
    lal.add(2, 1);
    lal.add(3, 2);
    lal.add(8);
    lal.add(5, 7);
    lal.add(6, 6);
    lal.add(7, 5);
    lal.add(8, 10);
    lal.add(8);
    lal.add(9);

    al.add(0, 0);
    al.add(3);
    al.add(2, 1);
    al.add(3, 2);
    al.add(8);
    al.add(5, 7);
    al.add(6, 6);
    al.add(7, 5);
    al.add(8, 10);
    al.add(8);
    al.add(9);

    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void get() {
    add();
    assertEquals(al.get(3), lal.get(3));
  }

  @org.junit.jupiter.api.Test
  void set() {
    add();
    assertEquals(al.set(3, 11), lal.set(3, 11));
    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void remove() {
    add();
    assertEquals(al.remove(1), lal.remove(1));
    assertEquals(al.remove(new Integer(3)), lal.remove(new Integer(3)));
    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void size() {
    add();
    assertEquals(al.size(), lal.size());
    assertEquals(al, lal);
  }


  @org.junit.jupiter.api.Test
  void indexOf() {
    add();
    assertEquals(al.indexOf(7), lal.indexOf(7));
    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void lastIndexOf() {
    add();
    assertEquals(lal.indexOf(8), al.indexOf(8));
    assertEquals(al, lal);
  }


  @org.junit.jupiter.api.Test
  void addAll() {
    add();
    LinkedList ll = new LinkedList();
    ll.add(3);
    ll.add(7);
    ll.add(6);
    assertEquals(lal.addAll(ll), al.addAll(ll));
    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void subList() {
    add();
    assertEquals(al.subList(3, 5), lal.subList(3, 5));
  }


  @org.junit.jupiter.api.Test
  void equals() {
    add();
    assertTrue(al.equals(lal));
  }

  java.util.ListIterator<Integer> alIter = al.listIterator();
  java.util.ListIterator<Integer> lalIter = lal.listIterator();

  @org.junit.jupiter.api.Test
  void nextIndex() {
    add();
    assertEquals(alIter.nextIndex(), lalIter.nextIndex());
  }

  @org.junit.jupiter.api.Test
  void previousIndex() {
    add();
    assertEquals(alIter.previousIndex(), lalIter.previousIndex());
  }

  @org.junit.jupiter.api.Test
  void hasPrevious() {
    add();
    assertEquals(alIter.hasPrevious(), lalIter.hasPrevious());
  }

  @org.junit.jupiter.api.Test
  void previous() {
    add();
    java.util.ListIterator<Integer> alIter = al.listIterator(5);
    java.util.ListIterator<Integer> lalIter = lal.listIterator(5);
    assertEquals(alIter.previous(), lalIter.previous());
  }

  @org.junit.jupiter.api.Test
  void hasNext() {
    add();
    assertEquals(alIter.hasNext(), lalIter.hasNext());
  }

  @org.junit.jupiter.api.Test
  void next() {
    add();
    java.util.ListIterator<Integer> alIter = al.listIterator(5);
    java.util.ListIterator<Integer> lalIter = lal.listIterator(5);
    assertEquals(alIter.next(), lalIter.next());
  }

  @org.junit.jupiter.api.Test
  void setForIterator() {
    add();
    java.util.ListIterator<Integer> alIter = al.listIterator(5);
    java.util.ListIterator<Integer> lalIter = lal.listIterator(5);
    alIter.next();
    lalIter.next();
    alIter.set(1);
    lalIter.set(1);
    assertEquals(al, lal);
  }

  @org.junit.jupiter.api.Test
  void addForIterator() {
    add();
    java.util.ListIterator<Integer> alIter = al.listIterator(5);
    java.util.ListIterator<Integer> lalIter = lal.listIterator(5);
    alIter.next();
    lalIter.next();
    alIter.add(1);
    lalIter.add(1);
    assertEquals(al, lal);
  }


  @org.junit.jupiter.api.Test
  void clear() {
    add();
    lal.clear();
    al.clear();
    assertEquals(al, lal);
  }
}
