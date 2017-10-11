/**
 * Jonathan Moyett
 * CSCI 3510 Project 6
 * 2/29/16
 */
package csci3510project6;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author someguy590
 */
public class Project6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String regex;
        int n;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Regular expression: ");
        Pattern p = Pattern.compile(input.nextLine());
        System.out.print("n: ");
        n = input.nextInt();
        
        if (n < 0) {
            System.out.println("Length must be zero or greater");
            return;
        }
        
        System.out.println("------- Strings in the language -------");
        
        // Initialize matcher
        Matcher matcher = p.matcher("");
        
        // Match empty string
        if (matcher.matches())
            System.out.println("\u03B5");
        
        // Check every string of length <= n
        for (int i = 1; i <= n; i++) {
            
            // Number of binary strings of length i
            double strCount = Math.pow(2, i);
            for (int j = 0; j < strCount; j++) { // j = decimal value of string
                
                // Convert j from decimal value to binary value
                String string = Integer.toBinaryString(j);
                
                // Pad zeroes to match length i
                while (string.length() < i)
                    string = "0" + string;
                
                matcher.reset(string);
                if (matcher.matches())
                    System.out.println(string);
                
            }
        }
    }
    
}
