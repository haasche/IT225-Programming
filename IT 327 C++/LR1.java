/**
 * @author Eric Haasch
 * @date 10/17/16
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LR1 {
    private static String expression;
    private static String token = "";
    private static int expressionIndex = 0;
    private static int switchVar = -1;
    private static boolean valid = true;
    private static boolean complete = false;
    private static Stack < String > stack = new Stack < String > ();

    /**
     * pops the stack and prints out what is left in the input
     * @return String result
     */
    private static String pop() {
        String result = stack.pop();
        System.out.println(stack.toString() + " " + token + expression.substring(expressionIndex));
        return result;
    }

    /**
     * pushes string to stack and prints out what is left in the input
     * @param s
     */
    private static void push(String s) {
        stack.push(s);
        System.out.println(stack.toString() + " " + token + expression.substring(expressionIndex));
    }

    /**
     * checks if a specific point is an operator
     * @param c
     * @return boolean
     */
    private static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '$')
            return true;
        else
            return false;
    }

    /**
     * checks if a specific point is an int and returns true if it is
     * @param c
     * @return boolean
     */
    private static boolean isInt(char c) {
        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
            return true;
        else
            return false;
    }

    /**
     * checks if a specific point is an int and returns true if it is
     * @param c
     * @return boolean
     */
    private static boolean isInt() {
        char c = token.charAt(0);
        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')
            return true;
        else
            return false;
    }

    /**
     * checks what the token is and classifies the token as operator or int 
     */
    private static void setToken() {
        if (isOperator(expression.charAt(expressionIndex))) {
            //a way of making token string
            token = "" + expression.charAt(expressionIndex);
            expressionIndex++;
        } else if (isInt(expression.charAt(expressionIndex))) {
            token = "";
            while (isInt(expression.charAt(expressionIndex))) {
                token += expression.charAt(expressionIndex);
                expressionIndex++;
            }
        } else {
            valid = false;
            expressionIndex++;
            setToken();
        }
    }

    /**
     * looks for what the token is and returns true if it is what the program is looking for.
     * @param c
     * @return boolean
     */
    private static boolean tokenIs(char c) {
        if (token.charAt(0) == c)
            return true;
        return false;
    }

    /**
     * looks at stack, sets switchVar to correct state for switch, does the parsing method specified
     */
    private static void switchVar() {
        if (isInt(stack.peek().charAt(0)))
            switchVar = Integer.parseInt(stack.peek());
        else {
            switchVar = -1;
        }
        switch (switchVar) {
            case 0:
                state0();
                break;
            case 1:
                state1();
                break;
            case 2:
                state2();
                break;
            case 3:
                state3();
                break;
            case 4:
                state4();
                break;
            case 5:
                state5();
                break;
            case 6:
                state6();
                break;
            case 7:
                state7();
                break;
            case 8:
                state8();
                break;
            case 9:
                state9();
                break;
            case 10:
                state10();
                break;
            case 11:
                state11();
                break;
            default:
                System.out.println("Unknown switchVar, please start over.");
                valid = false;
                break;
        }
    }

    /**
     * Pushes E + the next state to go to
     */
    private static void pushE() {
        if (stack.peek().equals("0")) {
            push("E");
            push("1");
        } else if (stack.peek().equals("4")) {
            push("E");
            push("8");
        } else
            valid = false;
    }

    /**
     * Pushes T + the next state to go to
     */
    private static void pushT() {
        if (stack.peek().equals("0")) {
            push("T");
            push("2");
        } else if (stack.peek().equals("4")) {
            push("T");
            push("2");
        } else if (stack.peek().equals("6")) {
            push("T");
            push("9");
        } else
            valid = false;
    }

    /**
     *Pushes F + the next state to go to
     */
    private static void pushF() {
        if (stack.peek().equals("0")) {
            push("F");
            push("3");
        } else if (stack.peek().equals("4")) {
            push("F");
            push("3");
        } else if (stack.peek().equals("6")) {
            push("F");
            push("3");
        } else if (stack.peek().equals("7")) {
            push("F");
            push("10");
        } else {
            valid = false;
        }
    }

    /**
     * Shifts 5 or 4 according to table
     */
    private static void state0() {
        if (isInt()) {
            push(token);
            push("5");
            setToken();
        } else if (tokenIs('(')) {
            push(token);
            push("4");
            setToken();
        } else {
            System.out.println("The input isn't Valid");
            System.exit(0);
        }
    }

    /**
     * Looks for + token or - token
     * Pushes 6
     * Otherwise looks for $, accepts and complete parse	
     * returns a false if what is found is not expected
     */
    private static void state1() {
        if (tokenIs('+') || tokenIs('-')) {
            push(token);
            push("6");
            setToken();
        } else if (tokenIs('$')) {
            complete = true;
        } else {
            valid = false;
        }
    }

    /**
     * Looks for + token or - token or ) token 
     * Pushes E from T (E->T)
     * Looks for * token or / token
     * Pushes 7
     * returns a false if what is found is not expected
     */
    private static void state2() {
        if (tokenIs('+') || tokenIs('-') || tokenIs(')') || tokenIs('$')) {
            pop();
            if (pop().charAt(0) != 'T') {
                valid = false;
            }
            pushE();
        } else if (tokenIs('*') || tokenIs('/')) {
            push(token);
            push("7");
            setToken();
        } else {
            valid = false;
        }
    }

    /**
     * Looks for operator token or ) token 
     * Pushes F from T (T->F)
     * returns a false if what is found is not expected
     */
    private static void state3() {
        if (tokenIs('+') || tokenIs('-') || tokenIs('*') || tokenIs('/') || tokenIs(')') || tokenIs('$')) {
            pop();
            if (pop().charAt(0) != 'F') {
                valid = false;
            }
            pushT();
        } else {
            valid = false;
        }
    }

    /**
     * Checks if token is int
     * pushes 5
     * Checks if ( token
     * Pushes 4
     * returns a false if what is found is not expected
     */
    private static void state4() {
        if (isInt()) {
            push(token);
            push("5");
            setToken();
        } else if (tokenIs('(')) {
            push(token);
            push("4");
            setToken();
        } else {
            valid = false;
        }
    }

    /**
     * Checks for operator token or ) token
     * Pushes F from id (F->id)
     * returns a false if what is found is not expected
     */
    private static void state5() {
        if (tokenIs('+') || tokenIs('-') || tokenIs('*') || tokenIs('/') || tokenIs(')') || tokenIs('$')) {
            pop();
            if (!isInt(pop().charAt(0))) {
                valid = false;
            }
            pushF();
        } else {
            valid = false;
        }
    }

    /**
     * Checks if token is int
     * pushes 5
     * Checks if token is ( 
     * pushes 4
     * returns a false if what is found is not expected
     */
    private static void state6() {
        if (isInt()) {
            push(token);
            push("5");
            setToken();
        } else if (tokenIs('(')) {
            push(token);
            push("4");
            setToken();
        } else {
            valid = false;
        }
    }

    /**
     * Checks if token is int
     * Pushes 5
     * Checks if token is (
     * pushes 4
     * returns a false if what is found is not expected
     */
    private static void state7() {
        if (isInt()) {
            push(token);
            push("5");
            setToken();
        } else if (tokenIs('(')) {
            push(token);
            push("4");
            setToken();
        } else {
            valid = false;
        }
    }

    /**
     * Looks for + or - token
     * Pushes 6
     * Looks for ) token
     * Pushes 11
     * returns a false if what is found is not expected
     */
    private static void state8() {
        if (tokenIs('+') || tokenIs('-')) {
            push(token);
            push("6");
            setToken();
        } else if (tokenIs(')')) {
            push(token);
            push("11");
            setToken();
        } else {
            valid = false;
        }
    }

    /**
     * Looks for + token or - token or T token or E token
     * Pushes E (E->E+T)
     * Looks for * token or / token
     * pushes 7
     * returns a false if what is found is not expected
     */
    private static void state9() {
        if (tokenIs('+') || tokenIs('-') || tokenIs(')') || tokenIs('$')) {
            pop();
            if (pop().charAt(0) != 'T') {
                valid = false;
            }
            pop();
            String popper = pop();
            if (popper.charAt(0) != '+') {
                valid = false;
            }
            //a way to make - work
            if (popper.charAt(0) == '-') {
                valid = true;
            }
            pop();
            if (pop().charAt(0) != 'E') {
                valid = false;
            }
            pushE();
        } else if (tokenIs('*') || tokenIs('/')) {
            push(token);
            push("7");
            setToken();
        } else
            valid = false;
    }

    /**
     * Looks for operator or ) token
     * pushes T (T->T*F)
     * returns a false if what is found is not expected
     */
    private static void state10() {
        if (tokenIs('+') || tokenIs('-') || tokenIs('*') || tokenIs('/') || tokenIs(')') || tokenIs('$')) {
            //T->T*F
            pop();
            if (pop().charAt(0) != 'F') {
                valid = false;
            }
            pop();
            String popper = pop();
            if (popper.charAt(0) != '*') {
                valid = false;
            }
            //a way to get / to work
            if (popper.charAt(0) == '/') {
                valid = true;
            }

            pop();
            if (pop().charAt(0) != 'T') {
                valid = false;
            }
            pushT();
        } else
            valid = false;
    }

    /**
     * Looks for operator or ( token or ) token
     * pushes F (F->(E))
     * returns a false if what is found is not expected
     */
    private static void state11() {
        if (tokenIs('+') || tokenIs('-') || tokenIs('*') || tokenIs('/') || tokenIs(')') || tokenIs('$')) {
            pop();
            if (pop().charAt(0) != ')') {
                valid = false;
            }
            pop();
            if (pop().charAt(0) != 'E') {
                valid = false;
            }
            pop();
            if (pop().charAt(0) != '(') {
                valid = false;
            }
            pushF();
        } else
            valid = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in .readLine();
        expression = line.concat("$");
        push("0");
        setToken();
        while (!complete && valid) {
            switchVar();
        }
        if (valid) {
            System.out.println("The input is Valid");
        } else {
            System.out.println("The input isn't Valid");
        }
    }
}