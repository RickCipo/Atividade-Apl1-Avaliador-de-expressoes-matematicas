import java.util.Scanner;

public class REPLCalculator {
    private static final int MAX_VARS = 26;
    private static final char[] variables = new char[MAX_VARS];
    private static final double[] values = new double[MAX_VARS];
    private static final String[] history = new String[100];
    private static int historyIndex = 0;
    private static boolean recording = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple REPL Calculator. Type EXIT to quit.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("EXIT")) break;
            processInput(input);
        }
        scanner.close();
    }

    private static void processInput(String input) {
        if (input.equals("VARS")) {
            listVariables();
        } else if (input.equals("RESET")) {
            resetVariables();
        } else if (input.equals("REC")) {
            recording = true;
        } else if (input.equals("STOP")) {
            recording = false;
        } else if (input.equals("PLAY")) {
            playHistory();
        } else if (input.equals("ERASE")) {
            eraseHistory();
        } else if (input.matches("[A-Z]=.*")) {
            assignVariable(input.charAt(0), input.substring(2));
        } else {
            evaluateExpression(input);
        }
    }

    private static void listVariables() {
        for (int i = 0; i < MAX_VARS; i++) {
            if (variables[i] != 0) {
                System.out.println(variables[i] + " = " + values[i]);
            }
        }
    }

    private static void resetVariables() {
        for (int i = 0; i < MAX_VARS; i++) {
            variables[i] = 0;
            values[i] = 0;
        }
        System.out.println("Variables reset.");
    }

    private static void playHistory() {
        for (int i = 0; i < historyIndex; i++) {
            processInput(history[i]);
        }
    }

    private static void eraseHistory() {
        for (int i = 0; i < historyIndex; i++) {
            history[i] = null;
        }
        historyIndex = 0;
        System.out.println("History erased.");
    }

    private static void assignVariable(char var, String expression) {
        int index = var - 'A';
        if (index < 0 || index >= MAX_VARS) {
            System.out.println("Invalid variable name.");
            return;
        }
        double result = evaluateExpression(expression);
        variables[index] = var;
        values[index] = result;
        System.out.println(var + " = " + result);
    }

    private static double evaluateExpression(String expression) {
        String postfix = infixToPostfix(expression);
        if (postfix == null) {
            System.out.println("Invalid expression.");
            return 0;
        }
        double result = evaluatePostfix(postfix);
        System.out.println(result);
        if (recording) {
            history[historyIndex++] = expression;
        }
        return result;
    }

    private static String infixToPostfix(String expression) {
        char[] stack = new char[100];
        int top = -1;
        StringBuilder output = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack[++top] = c;
            } else if (c == ')') {
                while (top >= 0 && stack[top] != '(') {
                    output.append(stack[top--]);
                }
                top--; 
            } else {
                while (top >= 0 && precedence(stack[top]) >= precedence(c)) {
                    output.append(stack[top--]);
                }
                stack[++top] = c;
            }
        }
        while (top >= 0) {
            output.append(stack[top--]);
        }
        return output.toString();
    }

    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    private static double evaluatePostfix(String postfix) {
        double[] stack = new double[100];
        int top = -1;
        for (char c : postfix.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = c - 'A';
                stack[++top] = values[index];
            } else if (Character.isDigit(c)) {
                stack[++top] = c - '0';
            } else {
                double b = stack[top--];
                double a = stack[top--];
                switch (c) {
                    case '+': stack[++top] = a + b; break;
                    case '-': stack[++top] = a - b; break;
                    case '*': stack[++top] = a * b; break;
                    case '/': stack[++top] = a / b; break;
                    case '^': stack[++top] = Math.pow(a, b); break;
                }
            }
        }
        return stack[top];
    }
}
