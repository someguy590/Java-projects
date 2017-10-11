package Chapter20_Collections;

import java.util.Comparator;

public class Exercise20_21 {

    /**
     * The method for sorting the numbers
     */
    public static <E> void selectionSort(E[] list,
            Comparator<? super E> comparator) {
        
        
        for (int i = 0; i < list.length; i++) {
            int min = i;
            int j;
            for (j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[min], list[j]) > 0) {
                    min = j;
                }
            }
            E temp = list[i];
            list[i] = list[min];
            list[min] = temp;
        }
    }

    public static void main(String[] args) {
        GeometricObject[] list1 = {new Circle(5), new Rectangle(4, 5),
            new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
            new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
            new Circle(6.5), new Rectangle(4, 5)};

        selectionSort(list1, new GeometricObjectComparator());
        for (GeometricObject g : list1) {
            System.out.print(g.getArea() + " ");
        }
        System.out.println();

        String[] list2 = {"red", "blue", "green", "yellow", "orange", "pink"};
        selectionSort(list2, (s1, s2) -> {
            return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
        });
        for (String s : list2) {
            System.out.print(s + " ");
        }
    }
}

// Copy GeometricObject, Circle, Rectangle classes from Chapter 13
abstract class GeometricObject {
  private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;

  /** Construct a default geometric object */
  protected GeometricObject() {
    dateCreated = new java.util.Date();
  }

  /** Construct a geometric object with color and filled value */
  protected GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }

  /** Return color */
  public String getColor() {
    return color;
  }

  /** Set a new color */
  public void setColor(String color) {
    this.color = color;
  }

  /** Return filled. Since filled is boolean,
   *  the get method is named isFilled */
  public boolean isFilled() {
    return filled;
  }

  /** Set a new filled */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /** Get dateCreated */
  public java.util.Date getDateCreated() {
    return dateCreated;
  }

  @Override
  public String toString() {
    return "created on " + dateCreated + "\ncolor: " + color +
      " and filled: " + filled;
  }

  /** Abstract method getArea */
  public abstract double getArea();

  /** Abstract method getPerimeter */
  public abstract double getPerimeter();
}

class GeometricObjectComparator
    implements Comparator<GeometricObject> {
    
  @Override
  public int compare(GeometricObject o1, GeometricObject o2) {
    double area1 = o1.getArea();
    double area2 = o2.getArea();

    if (area1 < area2)
      return -1;
    else if (area1 == area2)
      return 0;
    else
      return 1;
  }
}

class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
    this.radius = radius;
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }

  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /** Print the circle info */
  public void printCircle() {
    System.out.println("The circle is created " + getDateCreated() +
      " and the radius is " + radius);
  }
}




class Rectangle extends GeometricObject {
  private double width;
  private double height;

  public Rectangle() {
  }

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /** Return width */
  public double getWidth() {
    return width;
  }

  /** Set a new width */
  public void setWidth(double width) {
    this.width = width;
  }

  /** Return height */
  public double getHeight() {
    return height;
  }

  /** Set a new height */
  public void setHeight(double height) {
    this.height = height;
  }

  @Override /** Return area */
  public double getArea() {
    return width * height;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * (width + height);
  }
}