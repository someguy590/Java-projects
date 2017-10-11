/*
 * Author: Jonathan Moyett
 * CSCI 2410
 * Date: January 25, 2015
 */
package Chapter20_Collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author someguy590
 */
public class Exercise20_01 {
    /**
     * 
     * @param args 
     * @throws java.io.FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        Scanner input = new Scanner(new File("Lincoln.txt"));
        
        while (input.hasNext()) {
            // Prevent adding non-words and words that do not start with a letter to the list
            if (input.hasNext("[A-Z].*")) {
                System.out.println("1: " + input.next());
            }
            else {
                String word = input.next();
                System.out.println("2: " + word);
                word = word.toLowerCase();
                list.add(word);
            }
        }
        
        // Sort list then print out
        Collections.sort(list);
        //System.out.println(list);
        
        for (String word : list) {
            //System.out.println(word);
        }
    }
}