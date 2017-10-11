package Chapter25;

import java.util.ArrayList;

public class Exercise23_13{
    final static int SIZE = 200000;
    
    public static void main(String []args){
        System.out.println("Array Size\t| Selection sort\tRadix sort\tBubble sort\tMerge sort\tQuick sort\tHeap sort");
        System.out.println("----------------|----------------------------------------------------------------------------------------------------");
        
        long startTime;
        long endTime;
        long executionTime;
        for (int i = 0; i < 4; i++) {
            int nextSize = SIZE + (100000 * i);

            int[] array = new int[nextSize];
            for (int j = 0; j < array.length; j++) {
                array[j] = (int) (Math.random() * nextSize); // I assume the max range of integers grows with input size
            }
            
            System.out.print(nextSize + "\t\t| ");

            // Selection sort
            startTime = System.currentTimeMillis();
            selectionSort(array.clone());
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.print(executionTime + "\t\t\t");

             // Radix sort
             startTime = System.currentTimeMillis();
             radixSort(array.clone());
             endTime = System.currentTimeMillis();
             executionTime = endTime - startTime;
             System.out.print(executionTime + "\t\t");

             // Bubble sort
             startTime = System.currentTimeMillis();
             bubbleSort(array.clone());
             endTime = System.currentTimeMillis();
             executionTime = endTime - startTime;
             System.out.print(executionTime + "\t\t");

             // Merge sort
             startTime = System.currentTimeMillis();
             mergeSort(array.clone());
             endTime = System.currentTimeMillis();
             executionTime = endTime - startTime;
             System.out.print(executionTime + "\t\t");

             // Quick sort
             startTime = System.currentTimeMillis();
             quickSort(array.clone());
             endTime = System.currentTimeMillis();
             executionTime = endTime - startTime;
             System.out.print(executionTime + "\t\t");

             // Heap sort
             Integer[] heapSortArray = new Integer[nextSize];
             for (int j = 0; j < array.length; j++) {
                 heapSortArray[j] = array[j];
             }
             startTime = System.currentTimeMillis();
             heapSort(heapSortArray);
             endTime = System.currentTimeMillis();
             executionTime = endTime - startTime;
             System.out.println(executionTime);
         }
     }
     
    /** Selection sort */
    public static void selectionSort(int[] m) {
        for (int i = 0; i < m.length - 1; i++) {
            int currentMin = m[i];
            int currentMinIndex = i;
            for (int j = i + 1; j < m.length; j++) {
                if (currentMin > m[j]) {
                    currentMin = m[j];
                    currentMinIndex = j;
                }
            }
            
            if (currentMin != m[i]) {
                m[currentMinIndex] = m[i];
                m[i] = currentMin;
            }
        }
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
     
     /** Bubble sort */
     public static void bubbleSort(int[] list) {
        boolean needNextPass = true;
        for (int i = 1; i < list.length && needNextPass; i++) {
            needNextPass = false;
            for (int j = 0; j < list.length - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    
                    needNextPass = true;
                }
            }
        }
    }

    /** Merge sort */
    public static void mergeSort(int[] list) {
        if (list.length > 1) {
            // Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }

    /** Merge two sorted lists */
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }
    
    /** Quick sort */
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /**
     * Partition the array list[first..last]
     */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } 
        else
            return first;
    }
    
    /** Heap sort */
  public static <E extends Comparable<E>> void heapSort(E[] list) {
    // Create a Heap of integers
    Heap<E> heap = new Heap<E>();

    // Add elements to the heap
    for (int i = 0; i < list.length; i++)
      heap.add(list[i]);

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--)
      list[i] = heap.remove();
  }
}

class Heap<E extends Comparable<E>> {
  private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

  /** Create a default heap */
  public Heap() {
  }

  /** Create a heap from an array of objects */
  public Heap(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]);
  }

  /** Add a new object into the heap */
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
      }
      else
        break; // the tree is a heap now

      currentIndex = parentIndex;
    }
  }

  /** Remove the root from the heap */
  public E remove() {
    if (list.size() == 0) return null;

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size()) break; // The tree is a heap
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
      }
      else
        break; // The tree is a heap
    }

    return removedObject;
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return list.size();
  }
}