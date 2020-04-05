
public interface TextEditor {

  /**
   * Reads and constructs text from specified text file
   *
   * @param path filename to be read
   * @return if file reads successfully or not
   */
  boolean read(String path);

  /**
   * Adds given string at the specified position
   *
   * @param index position to be added
   * @param str   string to be added
   * @return true
   */
  boolean add(int index, String str);

  /**
   * Finds first occurrences of given string
   *
   * @param str string to be find
   * @return position of find result, -1 if file not contains given string
   */
  int find(String str);

  /**
   * Replaces given element with specified replacement
   *
   * @param target      element to replaced
   * @param replacement element to be replaced to
   * @return number of elements to be replaced
   */
  int replace(Character target, Character replacement);
}
