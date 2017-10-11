/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise14_25;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_25 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        double[] points = new double[10];
        double[] angles = new double[points.length / 2];
        Circle circle = new Circle(250, 250, 
                Math.random() * 300 + 10, Color.TRANSPARENT);
        circle.setStroke(Color.AQUA);
        
        double r = circle.getRadius();
        for (int i = 0; i < points.length / 2; i++) {
            double theta = Math.random() * (2 * Math.PI + 1);
            if (theta > (2 * Math.PI))
                theta = 2 * Math.PI;
            
            angles[i] = theta;
        }
        
        Arrays.sort(angles);
        
        for (int i = 0, j = 0; i < angles.length; i++, j += 2) {
            points[j] = circle.getCenterX() + r * Math.cos(angles[i]);
            points[j + 1] = circle.getCenterY() + r * Math.sin(angles[i]);
        }
        
        Polygon p = new Polygon(points);
        
        root.getChildren().add(p);
        root.getChildren().add(circle);
        
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void thetaSort(double[] theta) {
        double minX;
        double minY;
        int minIndexX;
        int minIndexY;
        for (int i = 0; i < theta.length - 2; i += 2) {
            minX = theta[i];
            minY = theta[i + 1];
            minIndexX = i;
            minIndexY = i + 1;
            
            for (int j = i + 2; j < theta.length; j += 2) {
                if (minX > theta[j]) {
                    minX = theta[j];
                    minY = theta[j + 1];
                    minIndexX = j;
                    minIndexY = j + 1;
                }
            }
            
            theta[minIndexX] = theta[i];
            theta[minIndexY] = theta[i + 1];
            theta[i] = minX;
            theta[i + 1] = minY;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

