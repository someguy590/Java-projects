/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter25;

import java.util.ArrayList;

/**
 *
 * @author someguy590
 */
public class radix {
    private final static int SIZE = 100;
    public static void main(String[] args) {
        int[] array = new int[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * SIZE);
        }
        
        System.out.println(423393 / 1000 / 60);
    }
    
    /** Radix sort */
    public static void radixSort(int[] list) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        
        // Add the ten buckets to the list
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        
        int radixPosition = 0;
        while (buckets.get(0).size() != list.length) { // Stop when all numbers are in same bucket
            // Clear each buckets' previous values
            for (int i = 0; i < buckets.size(); i++) {
                buckets.get(i).clear();
            }
            
            // Add each number to appropriate bucket based on radix position
            for (int i = 0; i < list.length; i++) {
                int key = list[i] / (int)(Math.pow(10, radixPosition)) % 10;
                buckets.get(key).add(list[i]);
            }
            radixPosition++;
            
            // Add numbers back to list
            int k = 0; // k is an index for list
            for (int i = 0; i < buckets.size(); i++) {
                for (int j = 0; j < buckets.get(i).size(); j++) {
                    list[k++] = buckets.get(i).get(j);
                }
            }
        }
    }
}
