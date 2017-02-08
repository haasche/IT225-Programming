/**
 * IT 327, Fall 2016
 * @author Eric Haasch   
 * Date: 10/6/2015
 *  
 */

package edu.ilstu;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class LL1 {

    static String str;
    static StringTokenizer sttTkn;
    
    //E -> TE`
    static int parseE() {
        int n = parseT();
        return parseE1(n);
    }

    //E` -> TE`
    static int parseE1(int n) {
        if (str == "+") {
            next();
            int x = parseT();
            return parseE1(n + x);
        } else if (str == ")" || str == "$") {
            return n;
        } else if (str == "-") {
            next();
            int x = parseT();
            return parseE1(n - x);
        } else if (str == ")" || str == "$") {
            return n;
        } else {
            return n;
        }
    }

    //T -> FT`
    static int parseT() {
        int n = parseF();
        return parseT1(n);
    }

    //T` -> */FT` 
    static int parseT1(int n) {
        if (str == "*") {
            next();
            int x = parseF();
            return parseT1(n * x);
        } else if (str == "+" || str == "-" || str == ")" || str == "$") {
            return n;
        }
        if (str == "/") {
            next();
            int x = parseF();
            return parseT1(n / x);
        } else if (str == "+" || str == "-" || str == ")" || str == "$") {
            return n;
        } 
        else {
            return n; 
        }
    }

    // F -> (E)
    static int parseF() {
        if (str == "(") {
            next();
            int n = parseE();
            if (str == ")") {
                next();
                return n;
            } else {
                System.out.println("No");
                System.exit(-1);
                return -1; 
            }
        } else try {
            int n = Integer.parseInt(str);
            next();
            return n;
        } catch (NumberFormatException e) {
            System.out.println("No");
            System.exit(-1);
            return -1;
        }
    }
    
    /*
     * Read the string
     * Get the next token
     * put it in str
     */
    static void next() {
        try {
            str = sttTkn.nextToken().intern();
        } catch (NoSuchElementException e) {
            str = null;
        }
    }
    
    //Main driver
    public static void main(String args[]) throws IOException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        String newLine = scanner.nextLine();
        String newLine2 = "";
        
        //separates out numbers
        String[] result = newLine.split("(?<=[-+*/()])|(?=[-+*/()])");
        
        //used to add spaces to make program work
        for (int i = 0; i < result.length; i++) {
            String temp = result[i] + " ";
            newLine2 = newLine2 + temp;
        }
        
        //adds string to tokenizer, appends end of file ($) to it
        sttTkn = new StringTokenizer(newLine2 + " $");
        next();
        
        //start the parsing
        int n = parseE();
        
        /*
         * if $ is found
         * 		accept parse
         * 		print out ok
         * if $ is not found
         * 		decline parse
         * 		print out false
         */
        if (str == "$") {
            System.out.print("OK");
        } else {
            System.out.print("No");
            System.exit(-1);
        }
    }
}