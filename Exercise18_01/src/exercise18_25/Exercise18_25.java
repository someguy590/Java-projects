/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise18_25;

import java.math.BigInteger;

/**
 *
 * @author someguy590
 */
public class Exercise18_25 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        displayPermutation("abcd");
    }
    
    public static void displayPermutation(String s) {
        displayPermutation(" ", s);
    }
    
    private static void displayPermutation(String s1, String s2) {
        if (s2.length() <= 0)
            System.out.println(s1);
        
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i);
            displayPermutation(s1 + ch, s2.replace(String.valueOf(ch), ""));
            System.out.println(i);
        }
        
        
    }
    
}
