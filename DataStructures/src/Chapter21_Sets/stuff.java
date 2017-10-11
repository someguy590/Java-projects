/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter21_Sets;

import java.util.ArrayList;

/**
 *
 * @author someguy590
 */
public class stuff {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1);
        
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(-1);
        list2.add(-2);
        list2.add(-3);
        System.out.println(list2);
        
        list1.add(0, 0);
        System.out.println(list1);
        
        list1.addAll(0, list2);
        System.out.println(list1);
        
        list1.remove(3);
        list1.add(3, 10);
        System.out.println(list1);
    }
}
