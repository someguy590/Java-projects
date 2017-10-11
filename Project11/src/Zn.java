/**
 * Jonathan Moyett
 * CSCI 3330 Project 11
 * 4/20/2016
 */
public class Zn extends Ring {
    public static int n = 3; // Arbitrary value
    private int element; // Equivalence class

    // construct a Zn element from integer
    public Zn(int a) {
        element = a % n;
    }

    @Override
    public Ring add(Ring other) {
        Zn o = ((Zn) other);

        int newElement = (element + o.element) % n;

        return new Zn(newElement);
    }

    @Override
    public Ring sub(Ring other) {
        Zn o = ((Zn) other);

        int newElement = (element - o.element) % n;

        return new Zn(newElement);
    }

    @Override
    public Ring mul(Ring other) {
        Zn o = ((Zn) other);

        int newElement = (element * o.element) % n;

        return new Zn(newElement);
    }

    // return a string in the form "[3]"
    @Override
    public String toString() {
        return "[" + element + "]";
    }
}