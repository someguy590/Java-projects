package Chapter25;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jm4386
 */
public class Exercise25_23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfObjects;
        int[] objects;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the number of objects: ");
        numberOfObjects = input.nextInt();
        objects = new int[numberOfObjects];
        
        System.out.print("Enter the weights of the objects: ");
        for (int i = 0; i < numberOfObjects; i++) {
            objects[i] = input.nextInt();
        }
        
        ArrayList<ArrayList<Integer>> containers = new ArrayList<>();
        containers.add(new ArrayList<>());
        containers.get(0).add(objects[0]);
        
        for (int i = 1; i < objects.length; i++) {
            boolean isAdded = false;
            for (int j = 0; j < containers.size() && !isAdded; j++) {
                int containerWeight = sum(containers.get(j));
                
                if (objects[i] + containerWeight <= 10) {
                    int maxWeight = max(containerWeight, objects, i);
                    containers.get(j).add(maxWeight);
                    isAdded = true;
                }
            }
            
            if (!isAdded) {
                ArrayList<Integer> nextContainer = new ArrayList<>();
                nextContainer.add(objects[i]);
                containers.add(nextContainer);
            }
        }
        
        for (int i = 0; i < containers.size(); i++) {
            System.out.print("Container " + (i + 1) + " contains objects with weight " );
            for (int j = 0; j < containers.get(i).size(); j++) {
                System.out.print(containers.get(i).get(j) + " ");
            }
            System.out.println();
        }
        
        System.out.println("The optimal number of bins is " + containers.size());
    }
    
    public static int sum(ArrayList<Integer> container) {
        int sum = 0;
        for (Integer object : container) {
            sum = sum + object;
        }
        return sum;
    }
    
    public static int max(int containerWeight, int[] objects, int startIndex) {
        int currentMax = objects[startIndex];
        int currentMaxIndex = startIndex;
        for (int i = startIndex + 1; i < objects.length; i++) {
            if (objects[i] + containerWeight <= 10 && objects[i] > currentMax) {
                currentMax = objects[i];
                currentMaxIndex = i;
            }
        }
        
        if (currentMax == objects[startIndex]) {
            return objects[startIndex];
        }
        
        objects[currentMaxIndex] = objects[startIndex];
        objects[startIndex] = currentMax;
        
        return currentMax;
    }
}