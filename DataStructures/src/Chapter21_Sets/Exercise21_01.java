/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter21_Sets;

import java.util.LinkedHashSet;
import java.util.Arrays;
public class Exercise21_01 {

    public static void main(String[] args) {
        LinkedHashSet<String> set1 = new LinkedHashSet<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
        LinkedHashSet<String> set2 = new LinkedHashSet<>(Arrays.asList(new String[]{"George", "Katie", "Kevin", "Michelle", "Ryan"}));
        
        LinkedHashSet<String> union = (LinkedHashSet)set1.clone();
        LinkedHashSet<String> difference = (LinkedHashSet)set1.clone();
        LinkedHashSet<String> intersection = (LinkedHashSet)set1.clone();
        
        // Holds values of both sets
        union.addAll(set2);
        
        // I assume the set difference is set1 - set2
        difference.removeAll(set2);
        
        // Only holds values both sets have
        intersection.retainAll(set2);
        
        System.out.println("The union of the two sets is " + union);
        System.out.println("The difference of the two sets is " + difference);
        System.out.println("The intersection of the two sets is " + intersection);
    }
}