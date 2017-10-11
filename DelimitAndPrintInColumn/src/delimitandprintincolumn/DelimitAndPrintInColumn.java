/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delimitandprintincolumn;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author someguy590
 */
public class DelimitAndPrintInColumn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter("attributes.txt");
        
        System.out.println("Enter next phrase to delimit");
        
        
        String phrase = input.nextLine();
        while (!phrase.equals("stop")) {
            ArrayList<String> words = new ArrayList<>();
            words.addAll(Arrays.asList(phrase.split(",")));
            
            for (String word : words) {
                output.println(word.trim());
            }
            
            System.out.println("Enter next phrase to delimit");
            phrase = input.nextLine();
        }
        
        output.close();
        input.close();
    }
}
