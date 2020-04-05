
import java.util.AbstractList;

/**
 * This class has methods with loop.
 * List option can be made with calling the constructor with appropriate parameter.
 */
public class TextEditorLoop extends SimpleTextEditor implements TextEditor {

  public TextEditorLoop(AbstractList<Character> text) {
    super(text);
  }

  /**
   * Reads and constructs text from specified text file
   *
   * @param path filename to be read
   * @return if file reads successfully or not
   */
  @Override
  public boolean read(String path) {
    //TODO: check if path is not empty
    String str = readFile(path);

    for (int i = 0; i < str.length(); i++)
      text.add(str.charAt(i));

    return true;
  }

  /**
   * Adds given string at the specified position
   *
   * @param index position to be added
   * @param str   string to be added
   * @return true
   */
  @Override
  public boolean add(int index, String str) {
    rangeCheckForIndex(index);

    for (int i = 0; i < str.length(); i++) {
      text.add(index++, str.charAt(i));
    }

    return true;
  }


  /**
   * Finds first occurrences of given string
   *
   * @param str string to be find
   * @return position of find result, -1 if file not contains given string
   */
  @Override
  public int find(String str) {
    return find(0, str);
  }

  /**
   * Finds first occurrences of given string, after specified position
   *
   * @param index search will be made after this index
   * @param str   string to be find
   * @return position of find result, -1 if file not contains given string
   */
  @Override
  public int find(int index, String str) {
    checkForFind(str);
    for (int i = index; i < text.size(); i++) {

      int j = 0;
      int startingIndex = i;

      while (j < str.length() && text.get(i++).equals(str.charAt(j++))) {
        if (j == str.length()) return startingIndex;
      }
      i = startingIndex;

    }
    return -1;
  }

}
