/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter22_Algorithms;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Point2D;
/**
 *
 * @author jm4386
 */
public class Exercise22_09 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int size;
        Scanner input = new Scanner(System.in);
        double [][] points;
        ArrayList<Point2D> convexHull;
        
        System.out.print("How many points are in the set? ");
        size = input.nextInt();
        points = new double[size][2];
        
        System.out.print("Enter " + size + " points: ");
        for (int i = 0; i < size; i++) {
            points[i][0] = input.nextDouble();
            points[i][1] = input.nextDouble();
        }
        
        convexHull = getConvexHull(points);
        
        System.out.print("The convex hull is ");
        
        for (Point2D point : convexHull) {
            System.out.print("(" + point.getX() + ", " + point.getY() + ") ");
        }
        System.out.println();
        
    }
    
    /** Return the points that form a convex hull */
    public static ArrayList<Point2D> getConvexHull(double[][] s) {
        ArrayList<Point2D> hullPoints = new ArrayList<>();
        
        // Get rightmost top point to start wrapping
        Point2D rightTopPoint =  new Point2D(s[0][0], s[0][1]);
        for (int i = 1; i < s.length; i++) {
            if (rightTopPoint.getY() < s[i][1]) {
                rightTopPoint = new Point2D(s[i][0], s[i][1]);
            }
            else if (rightTopPoint.getY() == s[i][1] && rightTopPoint.getX() < s[i][0]) {
                rightTopPoint = new Point2D(s[i][0], s[i][1]);
            }
        }
        hullPoints.add(rightTopPoint);
        
        Point2D t0 = rightTopPoint;
        boolean isConvex = false;
        while (!isConvex) {
            Point2D t1 = new Point2D(s[0][0], s[0][1]);
            if (t0.equals(t1)) {
                t1 = new Point2D(s[1][0], s[1][1]);
            }
            for (int i = 0; i < s.length; i++) {
                Point2D p = new Point2D(s[i][0], s[i][1]);
                if ( (((t1.getX() - t0.getX()) * (p.getY() - t0.getY())) - 
                        ((p.getX() - t0.getX()) * (t1.getY() - t0.getY()))) > 0) {
                    t1 = p;
                }
            }
            
            if (t1.equals(rightTopPoint)) {
                isConvex = true;
            }
            else {
                hullPoints.add(t1);
                t0 = t1;
            }
        }
        
        return hullPoints;
    }
}