import java.util.Stack;

public class Postfix {

  /**
   * Calls another method with appropriate parameters
   *
   * @param s Postfix expression to be evaluated
   * @return result of the evaluation
   */
  public static Double evalPostfix(String s) {
    if (s.length() == 0) return null;
    return evalPostfix(s.split(" "), 0, new Stack<>());
  }

  /**
   * Actual method that performs the operation with required parameters
   *
   * @param arr   elements of postfix expression
   * @param index current index
   * @param stack operand stack
   * @return result of the evaluation
   */
  private static Double evalPostfix(String[] arr, int index, Stack<Double> stack) {
    if (index >= arr.length)
      return stack.pop();
    else if (isOperator(arr[index]))
      stack.push(result(stack.pop(), arr[index], stack.pop()));
    else
      stack.push(Double.valueOf(arr[index]));
    return evalPostfix(arr, index + 1, stack);
  }

  /**
   * Performs simple operation
   *
   * @param left  operand
   * @param c     operator
   * @param right operand
   * @return result
   */
  private static Double result(Double right, String c, Double left) {
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
