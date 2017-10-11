/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter22_Algorithms;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise22_13 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Pane that displays convex hull
        Pane hullPane = new Pane();
        
        // List that hold points and hull points
        ArrayList<Circle> points = new ArrayList<>();
        ArrayList<Circle> hullPoints = new ArrayList<>();
        
        // Pane that displays instructions
        VBox instructionPane = new VBox(new Label("INSTRUCTION"),new Label("Add: Left Click"), new Label("Remove: Right Click"));
        instructionPane.setPadding(new Insets(5));
        instructionPane.setStyle("-fx-border-color: black");
        
        // Mouse events
        hullPane.setOnMouseClicked((MouseEvent e) -> {
            // Left click - add point
            if (e.getButton() == MouseButton.PRIMARY) {
                Circle circle = new Circle(e.getX(), e.getY(), 5);
                hullPane.getChildren().add(circle);
                points.add(circle);
                getConvexHull(points, hullPoints, hullPane);
            }
            //Right click - remove point
            if (e.getButton() == MouseButton.SECONDARY) {
                // Only remove circle nodes
                if (e.getPickResult().getIntersectedNode() instanceof Circle) {
                    hullPane.getChildren().remove(e.getPickResult().getIntersectedNode());
                    points.remove(e.getPickResult().getIntersectedNode());
                }
                
                // Only redraw convex hull if a hull point was removed
                if (hullPoints.size() >= 3) { // Check if a hull exist
                    boolean isHullPoint = false;
                    for (int i = 0; i < hullPoints.size() && !isHullPoint; i++) {
                        if (e.getPickResult().getIntersectedNode().equals(hullPoints.get(i))) {
                            isHullPoint = true;
                        }
                    }
                    
                    if (isHullPoint)
                        getConvexHull(points, hullPoints, hullPane);
                }
            }
        });
        
        // Pane that holds instruction pane
        VBox leftPane = new VBox(instructionPane);
        leftPane.setPrefWidth(275);
        leftPane.setPadding(new Insets(10));
        
        // Root pane for scene
        HBox rootPane = new HBox(leftPane, hullPane);
        
        // Scene scene
        Scene scene = new Scene(rootPane, 300, 250);
        primaryStage.setTitle("Exercise22_13");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Allow hull pane to use rest of availible scene space
        hullPane.prefHeightProperty().bind(rootPane.heightProperty());
        hullPane.prefWidthProperty().bind(rootPane.widthProperty());
    }

    /**
     * Gets points that create convex hull, if any, and draws it, if possible
     */
    public static void getConvexHull(ArrayList<Circle> points, ArrayList<Circle> hullPoints, Pane hullPane) {
        hullPoints.clear();
        // If less there are less than 3 points, erase hull lines
        if (points.size() < 3) {
            drawConvexHull(points, hullPoints, hullPane);
            return;
        }
        
        // Get first hull point
        hullPoints.add(getRightBottomPoint(points));
        
        // Search for next hull point t1 starting from a previous hull point t0
        Circle t0 = hullPoints.get(0);
        boolean isConvex = false;
        // Until t1 is equal to another hull point point, the convex hull has not been found
        while (!isConvex) {
            // Aribtrary guess of t1
            Circle t1 = points.get(0);
            // Check if any points are to the left of the line between t0 and t1
            for (int i = 0; i < points.size(); i++) {
                Circle p = points.get(i);
                if (whichSide(t0, t1, p) > 0) {
                    t1 = p; // Change line to include the point
                }
                else if (whichSide(t0, t1, p) == 0) {
                    if (distance(t0, p) > distance(t0, t1))
                        t1 = p; // Change line to include fewest points on hull
                }
            }
            
            // If the next hull point is another hull point, the convex hull has been achieved
            if (t1.equals(hullPoints.get(0))) {
                isConvex = true;
            } 
            else {
                hullPoints.add(t1);
                t0 = t1;
            }
        }
        
        // Draw hull
        if (hullPoints.size() >= 3) {
            drawConvexHull(points, hullPoints, hullPane);
        }
    }
    
    /** Draws and erases convex hull as appropriate */
    public static void drawConvexHull(ArrayList<Circle> points, ArrayList<Circle> hullPoints, Pane hullPane){
        // Clear previous hull lines and only redraw if there are enough points
        hullPane.getChildren().retainAll(points);
        if (points.size() < 3) {
            return;
        }
        
        // Create the lines between hull points
        Circle p0 = hullPoints.get(0);
        for (int i = 1; i < hullPoints.size(); i++) {
            Circle p1 = hullPoints.get(i);
            hullPane.getChildren().add(new Line(p0.getCenterX(), p0.getCenterY(), p1.getCenterX(), p1.getCenterY()));
            
            p0 = p1;
        }
        // Add last line of convex hull
        Circle p1 = hullPoints.get(0);
        hullPane.getChildren().add(new Line(p0.getCenterX(), p0.getCenterY(), p1.getCenterX(), p1.getCenterY()));
    }
    
    /**
     * Return the rightmost lowest point
     */
    public static Circle getRightBottomPoint(ArrayList<Circle> list) {
        Circle currentRightTopPoint = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            double currentRightTopPointX = currentRightTopPoint.getCenterX();
            double currentRightTopPointY = currentRightTopPoint.getCenterY();
            
            Circle nextPoint = list.get(i);
            double nextPointX = nextPoint.getCenterX();
            double nextPointY = nextPoint.getCenterY();
            
            if (currentRightTopPointY < nextPointY) {
                currentRightTopPoint = nextPoint;
            } 
            else if (currentRightTopPointY == nextPointY && currentRightTopPointX < nextPointX) {
                currentRightTopPoint = nextPoint;
            }
        }
        
        return currentRightTopPoint;
    }
    
    /**
     * Tells which side of the line between t0 and t1 point p is on
     */
    public static double whichSide(Circle t0, Circle t1, Circle p) {
        return (t1.getCenterX() - t0.getCenterX()) * (p.getCenterY()- t0.getCenterY()) - (p.getCenterX()- t0.getCenterX()) * (t1.getCenterY()- t0.getCenterY());
    }
    
    /**
     * Returns distance between p1 and p2
     */
    public static double distance(Circle p1, Circle p2) {
        return Math.sqrt((p1.getCenterX() - p2.getCenterX()) * (p1.getCenterX() - p2.getCenterX()) + 
                        (p1.getCenterY() - p2.getCenterY()) * (p1.getCenterY()- p2.getCenterY()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}