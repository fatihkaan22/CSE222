package com.company;

/**
 * It updates the array when new element added or removed
 *
 * @author Fatih Kaan Salgir
 */

public class DynamicArray<T> {
  private Object[] data;

  public DynamicArray() {
    this.data = new Object[0];
  }

  /**
   * Adds new element to array
   *
   * @param o Element to be added
   */
  public void add(T o) {
    Object[] arr = new Object[data.length + 1];
    System.arraycopy(data, 0, arr, 0, data.length);
    data = arr;
    data[data.length - 1] = o;
  }

  /**
   * Removes specified element from the array
   *
   * @param o Element to be removed
   */
  public void remove(T o) {
    int i = 0;
    while (data[i] != null && data[i] != o) i++; //find element
    while (i < data.length - 1) {
      data[i] = data[i + 1];
      i++;
    }

    Object[] arr = new Object[data.length - 1];
    System.arraycopy(data, 0, arr, 0, arr.length);
    data = arr;
  }

  public T get(int index) {
    final T t = (T) data[index];
    return t;
  }

}
