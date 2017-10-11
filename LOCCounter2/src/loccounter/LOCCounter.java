package loccounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/****************************************************************************
 * Name:    Jonathan Moyett
 * Date:    2/18/16
 * Description: Counts a user prompted Java source file's size in LOC. 
 * Designed for programs with Dr. Su's ASU Java LOC counting standard and
 * associated coding standard 
 ****************************************************************************/

public class LOCCounter {
    private static final String[] KEYWORDS = {
        "case", "do", "else", "enum", "for", "if",
        "private", "public", "switch", "while"};
    private static Stack<Integer> bracesTracker = new Stack<>();
    private static ArrayList<ArrayList<Integer>> partSizes = new ArrayList<>();
    
    
    /**
     * Main method
     * @param args file to be counted
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner input;
        
        input = new Scanner(new File("LOCCounter.java"));
        
        int totalLineCount = 0;
        int partID = 0;
        boolean isLineComment = false;
        boolean isPartBrace = false;
        
        while (input.hasNextLine()) {
            String line = trimComments(input.nextLine()).trim();
            int lineSize;
            
            // Skip single line comments and empty lines
            if ((line.startsWith("//") || line.isEmpty()))
                continue;
            
            // Start of block comment; next curly brace belongs to a part
            if (line.startsWith("/*")) {
                isLineComment = true;
                isPartBrace = true;
                continue;
            }
            
            // End of block comment
            if (line.endsWith("*/")) {
                isLineComment = false;
                continue;
            }
            
            // Skip if line in comment block
            if (isLineComment)
                continue;
            
            
            if (line.endsWith("{")) {
                if (isPartBrace && bracesTracker.empty()) {
                    partID = 0;
                    bracesTracker.push(partID++);
                    
                    partSizes.add(new ArrayList<>());
                    partSizes.get(partSizes.size() - 1).add(0);
                    isPartBrace = false;
                    
                }
                else if (isPartBrace) {
                    bracesTracker.push(partID++);
                    partSizes.get(partSizes.size() - 1).add(0);
                    isPartBrace = false;
                }
                else {
                    bracesTracker.push(-1);
                }
            }
            
            
            if (line.contains("\u007D")) { // Closing curly brace
                bracesTracker.pop();             
            }
            
            totalLineCount++;
            lineSize = countLineSize(line);
            for (int itemIndex : bracesTracker) {
                if (itemIndex == -1) // Opening for a non-part
                    continue;
                
                int partIndex = partSizes.size() - 1;
                ArrayList<Integer> part = partSizes.get(partIndex);
                int itemSize = part.get(itemIndex);
                part.set(itemIndex, itemSize + lineSize);
            }
        }
        System.out.println(partSizes);
        printResults();
    }
    
    /**
     * 
     * @param string
     * @return 
     */
    public static String trimComments(String string) {
        int commentIndex = string.indexOf("//");
        
        if (commentIndex < 0)
            return string;
        
        return string.substring(0, commentIndex);
    }
    
    /**
     * 
     * @param line
     * @return 
     */
    public static int countLineSize(String line) {
        int count = 0;
        
        // Count every curly brace pair
        if (line.contains("{"))
            count++;
        
        // Count for every occurrence of keywords
        if (isElementInLine(line, Arrays.asList(KEYWORDS)))
            count++;
        
        // Count for every occurrence of semicolons
        count += line.length() - line.replace(";", "").length();
        
        return count;
    }
    
    /**
     * 
     * @param line
     * @param list
     * @return 
     */
    public static boolean isElementInLine(String line, List<String> list) {
        
        for (String s : list) {
            if (line.contains(s))
                return true;
        }
        
        return false;
    }
    
    /**
     * 
     */
    public static void printResults() {
        System.out.printf("%-20s%-20s%-20s%-20s%n", "Part Name", "# of Items", "Part Size", "Total Size");
        int totalSize = 0;
        for (int i = 0; i < partSizes.size(); i++) {
            int itemCount = partSizes.get(i).size() - 1;
            int partSize = partSizes.get(i).get(0);
            System.out.printf("%-20s%-20d%-20d%n", "Class" + i, itemCount, partSize);
            totalSize += partSize;
        }
        System.out.printf("%-20s%-20s%-20s%-20d%n", "", "", "", totalSize);
    }
}

/**
 * this is a second class
 * @author someguy590
 */
class SecondClass {
    int a;
    int b;
    
    /**
     * some kind of method
     * @param args 
     */
    public static void what(String args[]) {
        
    }
}