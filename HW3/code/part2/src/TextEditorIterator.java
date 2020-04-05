
import java.util.*;

/**
 * This class has Iterator methods.
 * List option can be made with calling the constructor with appropriate parameter.
 */
public class TextEditorIterator extends SimpleTextEditor implements TextEditor {

  public TextEditorIterator(AbstractList<Character> text) {
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
    String str = readFile(path);
    if (str == null) return false;

    for (Character c : str.toCharArray())
      text.add(c);

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

    ListIterator<Character> iter = text.listIterator(index);
    for (Character c : str.toCharArray()) {
      iter.add(c);
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
    rangeCheckForIndex(index);
    ListIterator<Character> iter = text.listIterator(index);
    while (iter.hasNext()) {
      iter.next();

      ListIterator<Character> checkIter = text.listIterator(iter.previousIndex());
      int i = 0;

      while (checkIter.hasNext() && checkIter.next().equals(str.charAt(i))) {
        i++;
        if (i == str.length()) return iter.nextIndex() - 1;
      }

    }
    return -1;
  }

}
