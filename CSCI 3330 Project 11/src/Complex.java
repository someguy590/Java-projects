/**
 * Jonathan Moyett
 * CSCI 3330 Project 11
 * 4/20/2016
 */
public class Complex extends Field {
    private double a; // Real part
    private double b; // Imaginary part

    // construct the complex number x + yi
    public Complex(double x, double y) {
        a = x;
        b = y;
    }

    @Override
    public Ring add(Ring other) {
        Complex o = (Complex)other;
        return new Complex(a + o.a, b + o.b);
    }

    @Override
    public Ring sub(Ring other) {
        Complex o = (Complex)other;
        return new Complex(a - o.a, b - o.b);
    }

    @Override
    public Ring mul(Ring other) {
        Complex o = (Complex)other;
        double newA = (a * o.a) - (b * o.b);
        double newB = (o.a * b) + (a * o.b);

        return new Complex(newA, newB);
    }

    @Override
    public Field div(Field other) {
        Complex o = (Complex)other;
        double denom = o.a * o.a + o.b * o.b;
        double newA = (a * o.a + b * o.b) / denom;
        double newB = (o.a * b - a * o.b) / denom;

        return new Complex(newA, newB);
    }

    // return a string in the form “-3+4i”
    @Override
    public String toString() {
        return a + "+" + b + "i";
    }
}
