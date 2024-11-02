package main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class PropertyChecker {

  public static Map<String, Object> checkProperty(String operation1, String operation2, int repetitions,
      int precision) {
    int correctCount = 0;

    // Create a DecimalFormat with forced decimal separator as '.'
    String formatStr = "#." + "#".repeat(precision);
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');
    DecimalFormat decimalFormat = new DecimalFormat(formatStr, symbols);

    Random random = new Random();

    for (int i = 0; i < repetitions; i++) {
      double x = Double.parseDouble(decimalFormat.format(random.nextDouble()));
      double y = Double.parseDouble(decimalFormat.format(random.nextDouble()));
      double z = Double.parseDouble(decimalFormat.format(random.nextDouble()));

      try {
        double result1 = evaluateExpression(operation1, x, y, z);
        double result2 = evaluateExpression(operation2, x, y, z);

        if (Double.compare(result1, result2) == 0) {
          correctCount++;
        }
      } catch (ArithmeticException e) {

      }
    }

    double successRate = (correctCount / (double) repetitions) * 100;

    Map<String, Object> result = new HashMap<>();
    result.put("operation1", operation1);
    result.put("operation2", operation2);
    result.put("repetitions", repetitions);
    result.put("precision", precision);
    result.put("success_rate", successRate);

    return result;
  }

  private static double evaluateExpression(String expression, double x, double y, double z) {
    // Replace variables with their values
    expression = expression.replace("x", String.valueOf(x))
        .replace("y", String.valueOf(y))
        .replace("z", String.valueOf(z));
    return evaluateSimpleExpression(expression);
  }

  private static double evaluateSimpleExpression(String expression) {
    // Remove whitespace for easier parsing
    expression = expression.replace(" ", "");

    // Using stacks to evaluate the expression
    Stack<Double> values = new Stack<>();
    Stack<Character> operators = new Stack<>();

    for (int i = 0; i < expression.length(); i++) {
      char currentChar = expression.charAt(i);

      // If the current character is a number, parse the whole number
      if (Character.isDigit(currentChar) || currentChar == '.') {
        StringBuilder number = new StringBuilder();
        while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
          number.append(expression.charAt(i++));
        }
        values.push(Double.parseDouble(number.toString()));
        i--; // Adjust index after the inner loop
      }
      // If the current character is an operator
      else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
        while (!operators.isEmpty() && hasPrecedence(currentChar, operators.peek())) {
          values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }
        operators.push(currentChar);
      }
      // If the current character is an opening parenthesis
      else if (currentChar == '(') {
        operators.push(currentChar);
      }
      // If the current character is a closing parenthesis
      else if (currentChar == ')') {
        while (operators.peek() != '(') {
          values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }
        operators.pop(); // Remove the '('
      }
    }

    // Final evaluation of the remaining operators in the stack
    while (!operators.isEmpty()) {
      values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
    }

    return values.pop();
  }

  private static boolean hasPrecedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')')
      return false;
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
      return false;
    return true;
  }

  private static double applyOperation(char op, double b, double a) {
    switch (op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0)
          throw new ArithmeticException("Division by zero");
        return a / b;
    }
    return 0;
  }

  public static void main(String[] args) {
    // Define possible combinations of operations
    String[][] operations = {
        { "(x + y) + z", "x + (y + z)" }, // Associativity
        { "x + y", "y + x" }, // Commutativity
        { "(x * y) * z", "x * (y * z)" }, // Multiplicative Associativity
        { "x * (y / z)", "(x * y) / z" } // Combination of operations
    };

    // Define different repetition counts and precision levels
    int[] repetitionsList = { 250, 500, 1000, 2000 };
    int[] precisionLevels = { 0, 2, 5, 7, 9, 12 };

    List<Map<String, Object>> results = new ArrayList<>();

    for (String[] op : operations) {
      for (int reps : repetitionsList) {
        for (int prec : precisionLevels) {
          Map<String, Object> result = checkProperty(op[0], op[1], reps, prec);
          results.add(result);
        }
      }
    }

    // Save results to CSV
    try (FileWriter csvWriter = new FileWriter("variability_results.csv")) {
      csvWriter.append("operation1,operation2,repetitions,precision,success_rate\n");
      for (Map<String, Object> result : results) {
        csvWriter.append(result.get("operation1").toString()).append(",")
            .append(result.get("operation2").toString()).append(",")
            .append(result.get("repetitions").toString()).append(",")
            .append(result.get("precision").toString()).append(",")
            .append(result.get("success_rate").toString()).append("\n");
      }
    } catch (IOException e) {

    }
  }
}
