/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter24;

import java.util.*;

public class PriorityQueueTest {
    private static final int SIZE = 5000000;
    
    public static void main(String[] args) {
        int[] numbers = new int[SIZE];
        PriorityUsingSortedArrayList<Integer> arrayPriority = new PriorityUsingSortedArrayList<>();
        PriorityUsingHeap<Integer> heapPriority = new PriorityUsingHeap<>();
        
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = (int) (Math.random() * SIZE);
        }
        
        // Priority queue using heap
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++)
            heapPriority.enqueue(numbers[i]);
        
        for (int i = 0; i < SIZE; i++)
            heapPriority.dequeue();
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        System.out.println("Priority queue using heap time: " + executionTime);
        
        // Priority queue using array list
        startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++)
            arrayPriority.enqueue(numbers[i]);
        
        for (int i = 0; i < SIZE; i++)
            System.out.println(arrayPriority.dequeue());
        
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        
        System.out.println("Priority queue using array list time: " + executionTime);
    }
    
    static class PriorityUsingHeap<E extends Comparable<E>> {
        private Heap<E> heap = new Heap<>();
        
        public void enqueue(E newObject) {
            heap.add(newObject);
        }
        
        public E dequeue() {
            return heap.remove();
        }
        
        public int getSize() {
            return heap.getSize();
        }
    }
    
    static class PriorityUsingSortedArrayList<E extends Comparable<E>> {
        private ArrayList<E> list = new ArrayList<>();
        
        public void enqueue(E newObject) {
            // Add newObject to end of list if list is empty
            if (list.isEmpty()) {
                list.add(newObject);
                return;
            }
            
            // Add newObject to end or start of list if equal to 
            if (!list.isEmpty()) {
                if (newObject.compareTo(list.get(list.size() - 1)) >= 0) {
                    list.add(newObject);
                    return;
                }
                
                if (newObject.compareTo(list.get(0)) <= 0) {
                    list.add(0, newObject);
                    return;
                }
            }
            
            int index = getInsertionIndex(list, newObject);
            list.add(index, newObject);
        }

        public E dequeue() {
            return list.remove(list.size() - 1);
        }

        public int getSize() {
            return list.size();
        }
        
        /** 
         * Modified binary search that returns the index of the first matched
         * key or closest index key could be added in according to natural ordering
         * of key
         */
        private <E extends Comparable<E>> int getInsertionIndex(ArrayList<E> list, E key) {
            int low = 0;
            int high = list.size() - 1;

            while (high >= low) {
                int mid = (low + high) / 2;
                if (key.compareTo(list.get(mid)) < 0)
                    high = mid - 1; 
                else if (key.equals(list.get(mid)))
                    return mid; 
                else
                    low = mid + 1;
            }

            return low; // Now high < low
        }
    }
    
    static class Heap<E extends Comparable<E>> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        
        /**
         * Create a default heap
         */
        public Heap() {
        }
        
        /**
         * Create a heap from an array of objects
         */
        public Heap(E[] objects) {
            for (int i = 0; i < objects.length; i++) {
                add(objects[i]);
            }
        }

        /**
         * Add a new object into the heap
         */
        public void add(E newObject) {
            list.add(newObject); // Append to the heap
            int currentIndex = list.size() - 1; // The index of the last node

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                // Swap if the current object is greater than its parent
                if (list.get(currentIndex).compareTo(
                        list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else {
                    break; // the tree is a heap now
                }
                currentIndex = parentIndex;
            }
        }

        /**
         * Remove the root from the heap
         */
        public E remove() {
            if (list.size() == 0) {
                return null;
            }

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                // Find the maximum between two children
                if (leftChildIndex >= list.size()) {
                    break; // The tree is a heap
                }
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(
                            list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }

                // Swap if the current node is less than the maximum
                if (list.get(currentIndex).compareTo(
                        list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else {
                    break; // The tree is a heap
                }
            }

            return removedObject;
        }

        /**
         * Get the number of nodes in the tree
         */
        public int getSize() {
            return list.size();
        }
    }
}
