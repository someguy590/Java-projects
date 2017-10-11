package psp.assignment.pkg3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Name: Jonathan Moyett
 * Date: February 22, 2016
 * Description: Calculates specified predictions using given data by linear
 * regression. 
 * 
 * This program is coded in the style of "Dr. Su's ASU Java Coding Standard".
 */
public class LinearRegressionCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Holds user data to calculate predictions from
        String[] dataTitles = new String[2];
        LinkedList<Double[]> dataSet = new LinkedList<>();
        ArrayList<Double> xEstimateValues = new ArrayList<>();
        ArrayList<Double> yPredictionValues = new ArrayList<>();
        double b0;
        double b1;
        
        // Ask for user input
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter title for x-values: ");
        dataTitles[0] = input.nextLine();
        System.out.println("Enter title for y-values: ");
        dataTitles[1] = input.nextLine();
        System.out.println();
        
        System.out.println("Enter total number of x-/y-value pairs: ");
        int xValueCount = input.nextInt();
        System.out.println();
        
        System.out.println("Enter all x-values: ");
        for (int i = 0; i < xValueCount; i++) {
            Double[] array = {input.nextDouble(), 0.0};
            dataSet.add(array);
        }
        System.out.println("Enter all y-values: ");
        for (int i = 0; i < xValueCount; i++) {
            dataSet.get(i)[1] = input.nextDouble();
        }
        System.out.println();
        
        System.out.println("Enter number of estimate values you want " +
                "predictions made for: ");
        int xEstimateValueCount = input.nextInt();
        System.out.println();
        
        System.out.println("Enter all estimate values you want predictions " +
                "made for: ");
        for (int i = 0; i < xEstimateValueCount; i++) {
            xEstimateValues.add(input.nextDouble());
        }
        System.out.println();
        
        // Calculate prediction values
        b1 = calculateB1(dataSet);
        b0 = calculateB0(dataSet);
        
        for (int i = 0; i < xEstimateValues.size(); i++) {
            double xEstimateValue = xEstimateValues.get(i);
            yPredictionValues.add(calculateY(b0, b1, xEstimateValue));
        }
        
        printPredictions(dataTitles, b0, b1, 
                xEstimateValues, yPredictionValues);
    }
    
    /**
     * Calculates the regression parameter b0 given a dataset in a list
     * @param dataSet
     * @return 
     */
    public static double calculateB0(List<Double[]> dataSet) {
        double b1 = calculateB1(dataSet);
        double xAvg = 0.0;
        double yAvg = 0.0;
        
        for (Double[] array : dataSet) {
            xAvg += array[0];
            yAvg += array[1];
        }
        xAvg = xAvg / dataSet.size();
        yAvg = yAvg / dataSet.size();
        
        return yAvg - b1 * xAvg;
    }
    
    /**
     * Calculates the regression parameter b1 given a dataset in a list
     * @param dataSet
     * @return 
     */
    public static double calculateB1(List<Double[]> dataSet) {
        double b1;
        
        int n = dataSet.size();
        double xAvg = 0.0;
        double yAvg = 0.0;
        double xTimesYSum = 0.0;
        double xSquaredSum = 0.0;
        
        for (Double[] array : dataSet) {
            double xValue = array[0];
            double yValue = array[1];
            
            xAvg += xValue;
            yAvg += yValue;
            
            xTimesYSum += xValue * yValue;
            xSquaredSum += Math.pow(xValue, 2);
            
        }
        xAvg = xAvg / n;
        yAvg = yAvg / n;
        
        b1 = (xTimesYSum - n * xAvg * yAvg) / 
                (xSquaredSum - n * Math.pow(xAvg, 2));
        
        return b1;
    }
    
    /**
     * Calculates and returns a prediction given an estimate and values b0, b1
     * @param b0
     * @param b1
     * @param x
     * @return 
     */
    public static double calculateY(double b0, double b1, double x) {
        return b0 + b1 * x;
    }
    
    /**
     * Prints to console the desired predictions of the estimates from xValues
     * @param dataTitles
     * @param b0
     * @param xValues
     * @param b1
     * @param yValues 
     */
    public static void printPredictions(String[] dataTitles, double b0, 
            double b1, List<Double> xValues, List<Double> yValues) {
        System.out.println("b0 = " + b0);
        System.out.println("b1 = " + b1);
        for (int i = 0; i < xValues.size() && i < yValues.size(); i++) {
            System.out.println(xValues.get(i) + " units of " + dataTitles[0] +
                    " predicts " + yValues.get(i) + " units of " + 
                    dataTitles[1]);
        }
    }
    
}