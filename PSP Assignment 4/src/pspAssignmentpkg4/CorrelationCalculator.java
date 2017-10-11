package pspAssignmentpkg4;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Jonathan Moyett
 * Date: February 24, 2016
 * Description: Calculates correlation data for a set of data
 * 
 * This program is coded in the style of "Dr. Su's ASU Java Coding Standard".
 */
public class CorrelationCalculator {
    private String xTitle = "x-values";
    private String yTitle = "y-values";
    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();
    private int dataSetSize = 0;
    private double r = -2;
    
    // Default constructor
    public CorrelationCalculator() {
        
    }
    
    public CorrelationCalculator(List<Double> xValues, List<Double> yValues) {
        this("x-values", "y-values", xValues, yValues);
        dataSetSize = xValues.size();
        calculate();
    }
    
    public CorrelationCalculator(String xTitle, String yTitle, 
            List<Double> xValues, List<Double> yValues) {
        this.xTitle = xTitle;
        this.yTitle = yTitle;
        this.xValues.addAll(xValues);
        this.yValues.addAll(yValues);
        dataSetSize = xValues.size();
        calculate();
    }
    
    /**
     * Sets new x values and calculates new r
     * @param xValues 
     */
    public void setXValues(List<Double> xValues) {
        this.xValues.clear();
        this.xValues.addAll(xValues);
        dataSetSize = xValues.size();
        
        calculate();
    }
    
    /**
     * Sets new x values and calculates new r value
     * @param yValues 
     */
    public void setYValues(List<Double> yValues) {
        this.yValues.clear();
        this.yValues.addAll(yValues);
        dataSetSize = yValues.size();
        
        calculate();
    }
    
    /**
     * Sets new title for x values
     * @param xTitle 
     */
    public void setXTitle(String xTitle) {
        this.xTitle = xTitle;
    }
    
    /**
     * Sets new title for y values
     * @param yTitle 
     */
    public void setYTitle(String yTitle) {
        this.yTitle = yTitle;
    }
    
    /**
     * Returns correlation variable r
     * @return 
     */
    public double getR() {
        return r;
    }
    
    /**
     * Returns correlation variable r^2
     * @return 
     */
    public double getRSquared() {
        return Math.pow(r, 2);
    }
    
    /**
     * Returns titles of x values
     * @return 
     */
    public String getXTitle() {
        return xTitle;
    }
    
    /**
     * Returns title of y values
     * @return 
     */
    public String getYTitle() {
        return yTitle;
    }
    
    /**
     * Returns the list of the set of x values
     * @return 
     */
    public List<Double> getXValues() {
        return xValues;
    }
    
    /**
     * Returns the list of the set of y values
     * @return 
     */
    public List<Double> getYValues() {
        return yValues;
    }
    
    /**
     * Returns current number of data pairs
     * @return 
     */
    public int getDataSetSize() {
        return dataSetSize;
    }
    
    /**
     * Calculates value of correlation variable r
     */
    public void calculate() {
        int n = dataSetSize;
        double xSum = 0.0;
        double ySum = 0.0;
        double xSquaredSum = 0.0;
        double ySquaredSum = 0.0;
        double xTimesYSum = 0.0;
        
        for (int i = 0; i < n; i++) {
            xSum += xValues.get(i);
            ySum += yValues.get(i);
            xSquaredSum += Math.pow(xValues.get(i), 2);
            ySquaredSum += Math.pow(yValues.get(i), 2);
            xTimesYSum += xValues.get(i) * yValues.get(i);
        }
        
        double nominator = n * xTimesYSum - xSum * ySum;
        double denominator = Math.sqrt(
                (n * xSquaredSum - Math.pow(xSum, 2)) * 
                (n * ySquaredSum - Math.pow(ySum, 2)));
        
        r = nominator / denominator;
    }
    
    /**
     * Print to console the title of the x/y values and the correlation
     * regression variables r and r^2
     */
    public void printVariables() {
        System.out.println("\"" + xTitle + "\""+ " vs " + "\"" + yTitle + "\"");
        System.out.println("r: " + getR());
        System.out.println("r^2: " + getRSquared());
    }
}
