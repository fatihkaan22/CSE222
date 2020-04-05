
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.stream.Stream;

/**
 * Abstract class which has some implementations and helper methods
 */
public abstract class SimpleTextEditor implements TextEditor {
  /**
   * All operations will be made over this List
   */
  protected AbstractList<Character> text;

  public SimpleTextEditor(AbstractList<Character> text) {
    this.text = text;
  }

  /**
   * print current text to System.out
   */
  public void print() {
    for (Character c : text) {
      System.out.print(c);
    }
  }

  /**
   * Reads text from file line by line
   *
   * @param path filename (path) to be read
   * @return contents of the file as string
   */
  protected String readFile(String path) {
    StringBuilder sb = new StringBuilder();
    try {
      Stream<String> stream = Files.lines(Paths.get(path));
      stream.forEach(s -> sb.append(s).append("\n"));

    } catch (IOException e) {
      e.printStackTrace();
    }

    return sb.toString();
  }

  /**
   * Checks if operation index is in bounds
   *
   * @param index index to be checked
   * @throws IndexOutOfBoundsException unless size is in bounds
   */
  protected void rangeCheckForIndex(int index) {
    if (index < 0 || index > text.size())
      throw new IndexOutOfBoundsException("Size is: " + text.size() + " Index is: " + index);
  }

  /**
   * Checks if given string is appropriate
   *
   * @param str String to checked for find
   * @throws IllegalArgumentException if search argument is empty
   * @throws NullPointerException     if search argument is null
   */
  protected void checkForFind(String str) {
    if (str == null)
      throw new NullPointerException("Search argument can not be null");
    if (str.equals(""))
      throw new IllegalArgumentException("Search argument can not be empty");
  }

  /**
   * Reads and constructs text from specified text file
   *
   * @param path filename to be read
   * @return if file reads successfully or not
   */
  @Override
  public abstract boolean read(String path);

  /**
   * Adds given string at the specified position
   *
   * @param index position to be added
   * @param str   string to be added
   * @return true
   */
  public abstract boolean add(int index, String str);

  /**
   * Finds first occurrences of given string, after specified position
   *
   * @param index search will be made after this index
   * @param str   string to be find
   * @return position of find result, -1 if file not contains given string
   */
  public int find(int index, String str) {
    throw new UnsupportedOperationException();
  }

  /**
   * Replaces given element with specified replacement
   *
   * @param target      element to replaced
   * @param replacement element to be replaced to
   * @return number of elements to be replaced
   */
  public int replace(Character target, Character replacement) {
    int noReplaced = 0;
    int targetIndex = 0;
    while (targetIndex != -1) {
      targetIndex = find(targetIndex, target.toString());
      if (targetIndex != -1) {
        text.set(targetIndex, replacement);
        noReplaced++;
      }
    }
    return noReplaced;
  }


}
