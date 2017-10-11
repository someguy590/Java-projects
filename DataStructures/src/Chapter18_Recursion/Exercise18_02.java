/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter18_Recursion;

import java.util.Scanner;

/**
 *
 * @author someguy590
 */
public class Exercise18_02 {
    public static void main(String[] args) {
        int m, n;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter 2 numbers to find greatest common factor:");
        System.out.println("First number:");
        m = input.nextInt();
        
        System.out.println("Second number:");
        n = input.nextInt();
        
        System.out.println("Greatest common factor of " + m + " and " + n + " : " + gcd(m,n));
        
    }
    
    public static int gcd(int m, int n) {
        if (m % n == 0)
            return n;
        else
            return gcd(n, m % n);
    }
}