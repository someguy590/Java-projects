package pspAssignmentpkg4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Name: Jonathan Moyett
 * Date: February 24, 2016
 * Description: Test class for CorrelationCalculator.java
 * 
 * This program is coded in the style of "Dr. Su's ASU Java Coding Standard".
 */
public class CorrelationCalculatorTest {
    /* Main Method */
    public static void main(String[] args) {
        String xTitle = "";
        String yTitle = "";
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();
        int n;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter title for x values: ");
        xTitle = input.nextLine();
        
        System.out.println("Enter title for y values: ");
        yTitle = input.nextLine();
        System.out.println();
        
        System.out.println("Enter amount of data values: ");
        n = input.nextInt();
        
        System.out.println("Enter all x values");
        for (int i = 0; i < n; i++)
            xValues.add(input.nextDouble());
        System.out.println("Enter all y values");
        for (int i = 0; i < n; i++)
            yValues.add(input.nextDouble());
        System.out.println();
        
        CorrelationCalculator calculator = new CorrelationCalculator(xTitle, 
                yTitle, xValues, yValues);
        
        calculator.calculate();
        
        calculator.printVariables();
    }
}