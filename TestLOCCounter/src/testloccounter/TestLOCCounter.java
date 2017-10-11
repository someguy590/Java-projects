/**
 * Test source file for the LOCCounter
 * Every semicolon in a line is counted
 * Only one keyword in a line is counted even if a string
 * Only one opening curly brace in a line is counted
 * 
 * Example: line String[] a = {"for", "if"};; is a size 4 line
 */
package testloccounter;

/**
 *
 * @author Jonathan Moyett
 */
public class TestLOCCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String string = "\\\"{}\"\"";
        
        if (string.contains("{"))
            System.out.println("contains a curley brace \"{\"");
        
        System.out.println("Expected size of programs may differ due to"
                + "mentioning of keywords (like for, if) in strings; this "
                + "helps simplify the program");
    }
    
    /**
     * 
     */
    static class InnerClass {
        int a;
        int b;
        String[] ab = {"{", "}", "\"{"
                + "}"};
    }
    
}

/** */
class SecondClass {
    int a;
    int b;
    String[] ab = {"{", "}", "\"{"
            + "}"};;;
    
    /** */
    public static void function1() {
        
    }
}
