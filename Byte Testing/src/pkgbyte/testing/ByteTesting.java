/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbyte.testing;

import java.util.Arrays;


/**
 *
 * @author someguy590
 */
public class ByteTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String s = "take";
        
        
        System.out.println(s.isEmpty());
        System.out.println(Arrays.toString(s.getBytes()));
        System.out.println((int)'¿');
        char c = 3;
        byte a = '\0';
        System.out.println(a);
        
    }
    
}
