public class Main {
  public static void main(String[] args) {

    System.out.println("Test Cases");
    System.out.println("T_1.1");
    System.out.println(reverse("this function writes the sentence in reverse"));
    System.out.println("T_1.2");
    System.out.println(reverse("oneWord"));
    System.out.println("T_1.3");
    System.out.println(reverse("")); //empty string

    System.out.println("T_2.1");
    System.out.println(isElfish("white leaf"));
    System.out.println("T_2.2");
    System.out.println(isElfish("not ELFish"));
    System.out.println("T_2.3");
    System.out.println(isElfish("")); //empty string

    int[] arr = {3, 5, 7, 1, 2, 4, 9, 8, 6, 0};
    int[] emptyArr = {};

    System.out.println("T_3.1");
    sort(arr);
    for (int value : arr)
      System.out.print(value + " ");
    System.out.println();

    System.out.println("T_3.2");
    sort(emptyArr);

    System.out.println("T_4.1");
    System.out.println(Postfix.evalPostfix("3 4 1 2 * - 2 / + 5 + 6 3 / -"));
    System.out.println("T_4.2");
    System.out.println(Postfix.evalPostfix(""));
    System.out.println("T_5.1");
    System.out.println(Prefix.evalPrefix("- + + 3 / - 4 * 1 2 2 5 / 6 3"));
    System.out.println("T_5.2");
    System.out.println(Prefix.evalPrefix(""));

    System.out.println("T_6.1");
    int[][] arr2D = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}};
    int[][] emptyArr2D = new int[][]{};

    printSpiral(arr2D);
    System.out.println("T_6.2");
    printSpiral(emptyArr2D);
  }


  /**
   * Prints the given 2D array spirally,
   * calls helper method with required parameters
   *
   * @param arr array to be printed spirally
   */
  public static void printSpiral(int[][] arr) {
    if (arr.length == 0 || arr[0].length == 1) return;
    printSpiral(arr, arr[0].length, arr.length, 0, 0, 0, 0, 0);
    System.out.println();
  }

  /**
   * Prints the given 2D array spirally
   * Actual method that performs the task with required parameters
   *
   * @param arr       array to be printed spirally
   * @param m         number of rows
   * @param n         number of columns
   * @param x         current positing in the row
   * @param y         current positing in the column
   * @param direction 0: left
   *                  1: down
   *                  2: right
   *                  3: up
   * @param loop      number of times finished a loop
   * @param noIter    number of iteration of method
   */
  private static void printSpiral(int[][] arr, int m, int n, int x, int y, int direction, int loop, int noIter) {
    if (noIter == arr.length * arr[0].length) return;
    if (direction == 0) {
      if (y == m) {
        direction = (direction + 1) % 4;
        printSpiral(arr, m, n, x + 1, y - 1, direction, loop, noIter);
        return;
      }
      System.out.print(arr[x][y] + " ");
      noIter++;
      printSpiral(arr, m, n, x, y + 1, direction, loop, noIter);
    } else if (direction == 1) {
      if (x == n) {
        direction = (direction + 1) % 4;
        printSpiral(arr, m, n, x - 1, y - 1, direction, loop, noIter);
        return;
      }
      System.out.print(arr[x][y] + " ");
      noIter++;
      printSpiral(arr, m, n, x + 1, y, direction, loop, noIter);
    } else if (direction == 2) {
      if (y == loop - 1) {
        direction = (direction + 1) % 4;
        printSpiral(arr, m, n, x - 1, y + 1, direction, loop, noIter);
        return;
      }
      System.out.print(arr[x][y] + " ");
      noIter++;
      printSpiral(arr, m, n, x, y - 1, direction, loop, noIter);
    } else if (direction == 3) {
      if (x == loop) {
        direction = (direction + 1) % 4;
        printSpiral(arr, m - 1, n - 1, x + 1, y + 1, direction, loop + 1, noIter);
        return;
      }
      System.out.print(arr[x][y] + " ");
      noIter++;
      printSpiral(arr, m, n, x - 1, y, direction, loop, noIter);
    }
  }


  /**
   * Sort given array increasing order
   * calls helper method with required parameters
   *
   * @param arr array to be sorted
   */
  public static void sort(int[] arr) {
    if (arr.length == 0) return;
    sort(arr, 0, 1, 0);
  }

  /**
   * Sort given array increasing order.
   * Actual method that performs the task with required parameters
   *
   * @param arr       array to be sorted
   * @param minIndex  minimum index
   * @param comp      compared value with minimum index
   * @param currIndex current position in the array
   */
  private static void sort(int[] arr, int minIndex, int comp, int currIndex) {
    if (currIndex == arr.length - 1) return;
    else if (comp == arr.length) {
      //swap
      int tmp = arr[minIndex];
      arr[minIndex] = arr[currIndex];
      arr[currIndex] = tmp;
      sort(arr, currIndex + 1, currIndex + 2, currIndex + 1);
      return;
    } else if (arr[minIndex] > arr[comp])
      minIndex = comp;
    sort(arr, minIndex, comp + 1, currIndex);
  }

  /**
   * Checks if given string is elfish (includes 'e', 'l', 'f'),
   * calls helper method with required parameters
   *
   * @param s given string
   * @return true if elfish, else otherwise
   */
  public static boolean isElfish(String s) {
    return isElfish(s, false, false, false);

  }

  /**
   * Checks if given string is elfish (includes 'e', 'l', 'f'),
   * Actual method that performs the task with required parameters
   *
   * @param s given string
   * @param e is includes e
   * @param l is includes l
   * @param f is includes f
   * @return true if elfish, else otherwise
   */
  private static boolean isElfish(String s, boolean e, boolean l, boolean f) {
    if (s.length() <= 0) return e && l && f;
    else if (s.charAt(s.length() - 1) == 'e') e = true;
    else if (s.charAt(s.length() - 1) == 'l') l = true;
    else if (s.charAt(s.length() - 1) == 'f') f = true;
    return isElfish(s.substring(0, s.length() - 1), e, l, f);
  }


  /**
   * Reverse the words in the string,
   * calls helper method with required parameters
   *
   * @param s string to be reversed
   * @return reversed string
   */
  public static String reverse(String s) {
    if (!s.contains(" ")) return s;
    return reverse(s, s.lastIndexOf(" "), new StringBuilder());
  }

  /**
   * Reverse the words in the string.
   * Actual method that performs the task with required parameters
   *
   * @param s              string to be reversed
   * @param lastSpaceIndex index of last space character
   * @param sb             append words here
   * @return reversed string
   */
  private static String reverse(String s, int lastSpaceIndex, StringBuilder sb) {
    if (lastSpaceIndex > 0) {
      sb.append(s.substring(lastSpaceIndex));
      reverse(s.substring(0, lastSpaceIndex), s.substring(0, lastSpaceIndex).lastIndexOf(" "), sb);
    }
    return sb.toString().trim();
  }
}
