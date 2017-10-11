package PSPAssignment1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Name: Jonathan Moyett
 * Date: February 22, 2016
 * Description: Calculates the mean and standard deviation for 
 * a list of real numbers
 * 
 * This program is coded in the style of "Dr. Su's ASU Java Coding Standard".
 */
public class MeanStdDevCalculator {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MeanStdDevCalculator m;
        LinkedList<Double> values = new LinkedList<>();
        
        int n;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter how many values of data in the list: ");
        n = input.nextInt();
        System.out.println();
        
        System.out.println("Enter all values of data in your list: ");
        for (int i = 0; i < n; i++)
            values.add(input.nextDouble());
        System.out.println();
        
        System.out.println("Mean: ");
        System.out.println(mean(values));
        System.out.println();
        
        System.out.println("Standard Deviation: ");
        System.out.println(stdDev(values, mean(values)));
        
        {
            //what
        }
    }
    
    /**
     * Calculates mean given a list of real numbers
     * 
     * @param values
     * @return 
     */
    public static double mean(List<Double> values) {
        double mean = 0.0;
        
        for (double d: values)
            mean += d;
        mean = mean / values.size();
        
        return mean;
    }
    
    /**
     * Calculates mean given a list of real numbers and mean
     * 
     * @param values
     * @param mean
     * @return 
     */
    public static double stdDev(List<Double> values, double mean) {
        double sum = 0.0;
        
        for (double d: values)
            sum += Math.pow(d - mean, 2);
        
        return Math.sqrt(sum / (values.size() - 1));
    }
    
}
