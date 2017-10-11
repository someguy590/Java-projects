package PSPAssginment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Name: Jonathan Moyett
 * Date: February 16, 2016
 * Description: Given a file to Java source code, this program will count
 * the size of the program in logical lines of code (LOC) according to
 * "ASU CSCI 5322 Java LOC standard" for programs that follow
 * "Dr. Su's ASU Java Coding Std."
 * The program is also assumed to be syntactically correct. 
 * 
 * Here a program "part" is considered a non-nested class. Nested classes and
 * methods are considered items. Each physical line of code may count as more
 * than one logical line of code if it contains certain elements like keywords
 * or terminating symbols.
 * 
 * Counting standard summary:
 * Every semicolon in a line is counted.
 * Only one appearance of a keyword in a line is counted;
 * if a line has more than one keyword only one is counted
 * Only one opening curly brace in a line is counted
 * Lines not in classes (imports, etc.) are added on to the part sizes to 
 * give total size
 * Example: Line String[] a = {"for", "if"};; counts as size 4.
 * 
 * To simplify the program, keywords are counted only once per line and 
 * whenever they appear even if they are used as a string or variable name.
 * This will cause a miscount in such cases.
 * 
 * This program is coded in the style of "Dr. Su's ASU Java Coding Standard".
 */
public class LOCCounter {
    private final static String[] KEYWORDS = {"case", "do", "else", "enum", 
            "for", "if", "private", "public", "switch", "while"};
    // Track scope that each brace belongs to
    private static Stack<String> braceTracker = new Stack<>();
    // Track each part in the program and number of items
    private static ArrayList<Integer[]> partSizes = new ArrayList<>();
    private static int totalProgramSize = 0;
    
    /** Main Method */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length <= 0) {
            System.out.println("Need to enter a file");
            return;
        }
        
        File file = new File(args[0]);
        
        if (!file.exists()) {
            System.out.println("File \"" + args[0] + "\" does not exist");
            return;
        }
        
        Scanner input = new Scanner(file);
        
        
        
        boolean isLineComment = false; // Do not count line if comment
        while (input.hasNextLine()) {
            // Remove whitespace and comment character "//"
            String line = trimComment(input.nextLine()).trim();
            
            // Skip empty or single line comments
            if (line.isEmpty() || line.startsWith("//"))
                continue;
            
            // Next "{" belongs to a part or item
            if (line.startsWith("/*")) {
                
                if (!line.endsWith("*/")) // Is a one line block comment
                    isLineComment = true;
                
                if (!braceTracker.isEmpty())
                    partSizes.get(partSizes.size() - 1)[1]++; // Count item
                
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
            if (containsStructureBrace(line)) {
                if (braceTracker.isEmpty()) {
                    Integer[] newPart = {0, 0};
                    partSizes.add(newPart);
                }
                braceTracker.push(line);
            }
            
            if (line.endsWith("}") || line.endsWith("};") || 
                    line.endsWith("});")) {
                braceTracker.pop();
            }
            
            int lineSize = countLineSize(line);
            totalProgramSize += lineSize;
            int partIndex = partSizes.size() - 1;
            if (partIndex < 0)
                continue;
            partSizes.get(partSizes.size() - 1)[0] += lineSize;
        }
        input.close();
        printProgramSize();
    }
    
    /**
     * Returns true if the string contains any string from the list
     * 
     * @param string
     * @param list
     * @return 
     */
    public static boolean containsListElement(
            String string, List<String> list) {
        
        for (String s : list) {
            if (string.contains(s))
                return true;
        }
        
        return false;
    }
    
    /**
     * Returns true if the line of code contains a opening curly brace used to 
     * define a scope as opposed to being used for its literal symbol as
     * a metacharacter in a regex or in a string.
     * 
     * @param line
     * @return 
     */
    public static boolean containsStructureBrace(String line) {
        if (!line.contains("{"))
            return false;
        
        // Character "{" exist outside of quotation marks
        if (line.matches("[^{]*\"(.*)\"[^{]*")) { // [^{]*"(.*)"[^{]*
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Counts logical size of a line of code
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
        if (containsStructureBrace(line))
            count++;
        
        // Count every semicolon in line
        count += line.length() - line.replace(";", "").length();
        
        return count;
    }
    
    /**
     * Deletes the part of a line of code that comes after the single line
     * comment character "//". Leaves any white space before the symbol.
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
    
    /**
     * Prints the total size of the program, the number of items per part, and
     * part sizes to console.
     */
    public static void printProgramSize() {
        System.out.printf("%-20s%-20s%-20s%n", 
                "Part Name", "# of Items", "Part Size");
        for (int i = 0; i < partSizes.size(); i++) {
            int itemCount = partSizes.get(i)[1];
            int partSize = partSizes.get(i)[0];
            System.out.printf("%-20s%-20d%-20d%n", 
                    "Class" + i, itemCount, partSize);
        }
        System.out.println("Total Program Size: " + totalProgramSize);
        
    }
}