/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loccounter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author someguy590
 */
public class LOCCounter2 {
    final static String[] KEYWORDS = {"case", "do", "else", "enum", 
            "for", "if", "private", "public", "switch", "while"};
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Stack<String> braceTracker = new Stack<>();
        ArrayList<Integer[]> partSizes = new ArrayList<>();
        Scanner input = new Scanner(new File("LOCCounter.java"));
        
        boolean isLineComment = false;
        while (input.hasNextLine()) {
            String line = trimComment(input.nextLine()).trim();
            
            // Skip empty or single line comments
            if (line.isEmpty() || line.startsWith("//"))
                continue;
            
            // Start of a comment block; next "{" belongs to a part or item
            if (line.startsWith("/*")) {
                isLineComment = true;
                
                if (!braceTracker.isEmpty())
                    partSizes.get(partSizes.size() - 1)[1]++;
                
                continue;
            }
            
            // End of comment block
            if (line.endsWith("*/")) {
                isLineComment = false;
                continue;
            }
            
            // Inside a comment block
            if (isLineComment)
                continue;
            
            // Track new part or track new item of current part
            if (line.contains("\u007B")) {
                System.out.println(line);
                if (braceTracker.isEmpty()) {
                    Integer[] newPart = {0, 0};
                    partSizes.add(newPart);
                }
                braceTracker.push(line);
            }
            
            int[] thing = {2, 0, 1};
            
            int[] thing2 = {2, 0, 
                1};
            
            //
            if (line.endsWith("}") || line.endsWith("};") || 
                    line.endsWith("});")) {
                braceTracker.pop();
            }
            
            int lineSize = countLineSize(line);
            
            
        }
        System.out.println(braceTracker);
        for (Integer[] a : partSizes) {
            System.out.println(Arrays.toString(a));
        }
    }
    
    /**
     * 
     * @param line
     * @param list
     * @return 
     */
    public static boolean containsListElement(String line, List<String> list) {
        
        for (String s : list) {
            if (line.contains(s))
                return true;
        }
        
        return false;
    }
    
    /**
     * 
     * @param line
     * @return 
     */
    public static int countLineSize(String line) {
        int count = 0;
        
        // Count for occurence of keyword
        if (containsListElement(line, Arrays.asList(KEYWORDS)))
            count++;
        
        // Count for pair of curly braces
        if (line.contains("\u007B"))
            count++;
        
        // Count every semicolon in line
        count += line.length() - line.replace(";", "").length();
        
        return count;
    }
    
    /**
     * 
     * @param line
     * @return 
     */
    public static String trimComment(String line) {
        if(!line.contains("//"))
            return line;
        
        int index = line.indexOf("//");
        
        return line.substring(0, index);
    }
}


/**
*/

class YeahVB {
	int a;
	int[] b = {1, 2, 3};
	
	/** 		*/
	public static maina() {
	}
	
	/**
	
	*/
	
	public static void notcountingthec{lassitslef?() {
	}
	
	
	
}