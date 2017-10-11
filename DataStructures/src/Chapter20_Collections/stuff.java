/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter20_Collections;

import java.util.TreeSet;

public class stuff {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        
        set.add(0);
        set.add(15);
        set.add(3);
        
        System.out.println(set);
    }
}