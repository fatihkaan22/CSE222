import java.util.Stack;

/**
 * Evaluation of prefix expression.
 */
public class Prefix {

  /**
   * Calls another method with appropriate parameters
   *
   * @param s Prefix expression to be evaluated
   * @return result of the evaluation
   */
  public static Double evalPrefix(String s) {
    if (s.length() == 0) return null;
    String[] arr = s.split(" ");
    return evalPrefix(arr, arr.length - 1, new Stack<>());
  }

  /**
   * Actual method that performs the operation with required parameters
   *
   * @param arr   elements of prefix expression
   * @param index current index
   * @param stack operand stack
   * @return result of the evaluation
   */
  private static Double evalPrefix(String[] arr, int index, Stack<Double> stack) {
    if (index < 0)
      return stack.pop();
    else if (isOperator(arr[index]))
      stack.push(result(stack.pop(), arr[index], stack.pop()));
    else
      stack.push(Double.valueOf(arr[index]));
    return evalPrefix(arr, index - 1, stack);
  }

  /**
   * Performs simple operation
   *
   * @param left  operand
   * @param c     operator
   * @param right operand
   * @return result
   */
  private static Double result(Double left, String c, Double right) {
    switch (c) {
      case "+":
        return left + right;
      case "-":
        return left - right;
      case "/":
        return left / right;
      case "*":
        return left * right;
      default:
        throw new UnsupportedOperationException("Operand is: " + c);
    }
  }

  /**
   * Checks if string is operator (+,-,*./)
   *
   * @param c elment
   * @return true if operand, false otherwise
   */
  private static boolean isOperator(String c) {
    return (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*"));
  }

}
