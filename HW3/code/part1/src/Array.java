public class Array<E> {
  E[] arr;
  /**
   * current size of the array
   */
  int size;

  /**
   * Constructs an empty array with size 0
   */
  public Array() {
    this.arr = (E[]) new Object[LinkedArrayList.arrayCapacity];
    this.size = 0;
  }

  /**
   * Constructs an array with specified element
   *
   * @param intialElement element to be constructed as first element of array
   */
  public Array(E intialElement) {
    this.arr = (E[]) new Object[LinkedArrayList.arrayCapacity];
    this.arr[0] = intialElement;
    this.size = 1;
  }

  /**
   * returns rest of the array by starting specified index
   *
   * @param fromIndex array will be constructed with elements after this index
   * @return array of elements after specified index
   * @throws IllegalStateException if array is not full, but trying to be copy
   */
  public Array<E> subList(int fromIndex) {
    if (this.size != LinkedArrayList.arrayCapacity) {
      throw new IllegalStateException("Error: Array is not full, but trying to be copy");
    }

    Array<E> newArray = new Array<>();
    System.arraycopy(this.arr, fromIndex, newArray.arr, 0, size - fromIndex);
    newArray.size = size - fromIndex;
    return newArray;
  }

  /**
   * It will remove all elements after specified index
   *
   * @param index elements will be removed after this index
   */
  public void removeAfter(int index) {
    for (int i = index; i < size; i++)
      arr[i] = null;
    size = index;
  }

  /**
   * Opens up space for adding new element middle of the array by shifting
   *
   * @param startIndex index will be emptied
   */
  private void enlarge(int startIndex) {
    if (size + 1 - startIndex >= 0)
      System.arraycopy(arr, startIndex, arr, startIndex + 1, size - startIndex);
  }

  /**
   * Adds element in the specified position
   *
   * @param index position to be added
   * @param e     element to be added
   * @return false if array is full, true otherwise
   * @throws IllegalStateException if current size of the array is not equal to index
   */
  public boolean add(int index, E e) {
    if (size == LinkedArrayList.arrayCapacity) { //array is full
      //create new node
      return false;
    } else if (size > index) {
      enlarge(index);
      arr[index] = e;
      this.size++;
      return true;
    } else {
      if (size != index) {
        throw new IllegalStateException("current size of the array is not equal to index");
      }
      arr[size] = e;
      this.size++;
      return true;
    }
  }

  /**
   * Returns the element at the specified position in this array.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this array
   */
  public E get(int index) {
    return arr[index];
  }

  /**
   * Replaces the element at the specified position in this array with the specified element.
   *
   * @param index index of the element to replace
   * @param e     element to be stored at the specified position
   */
  public void set(int index, E e) {
    arr[index] = e;
  }

  /**
   * Shifts element by 1 after specified index after remove
   *
   * @param index null index
   */
  private void shrink(int index) {
    System.arraycopy(arr, index + 1, arr, index, size - index - 1);
    size--;
    arr[size] = null;
  }

  /**
   * Removes the element at the specified position in this list.
   * Shifts the elements in the array if necessary.
   * Returns the element that was removed from the list.
   *
   * @param index the index of the element to be removed
   * @return the element previously at the specified position
   */

  public E remove(int index) {
    E e = arr[index];
    shrink(index);
    return e;
  }
}
