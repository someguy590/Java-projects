/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter18_Recursion;

/**
 *
 * @author someguy590
 */
public class Exercise18_05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i = 1; i < 11; i++) {
            System.out.println("m(" + i + ") = " + m(i));
        }
    }
    
    public static double m(int i) {
        if (i <= 1) {
            return 1.0/3;
        }
        else {
            return (i / (2.0 * i + 1.0) ) + m(i - 1);
        }
    }
    
}