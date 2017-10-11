/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package get_rid_of_line_numbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author someguy590
 */
public class GetRidOfLineNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        String fileName = "source.txt";
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        
        Scanner input = new Scanner(file);
        try (PrintWriter output = new PrintWriter("output.txt")) {
            int i = 1;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                line = line.trim();
                
                output.println(line);
                
                i++;
            }
        }
        input.close();
    }
}
