/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter19_Generics;

/**
 *
 * @author someguy590
 */
public class Exercise19_05 {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3};
        System.out.println(max(numbers));

        String[] words = {"red", "green", "blue"};
        System.out.println(max(words));

        Circle[] circles = {new Circle(3), new Circle(2.9), new Circle(5.9)};
        System.out.println(max(circles));
    }
    
    public static <E extends Comparable<E>> E max(E[] list) {
        E max = list[0];
        for (E e : list) {
            if (max.compareTo(e) == -1) {
                max = e;
            }
        }
        return max;
    }
    
    static class Circle implements Comparable<Circle> {
        double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public int compareTo(Circle c) {
            if (this.radius < c.radius) {
                return -1;
            } 
            else if (this.radius == c.radius) {
                return 0;
            } 
            else {
                return 1;
            }
        }
        
        @Override
        public String toString() {
            return "Circle radius: " + radius;
        }
    }
}