/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise14_29;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_29 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        final double NUMBER_OF_NODE_ROWS = 7;
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 500);
        
        double[] ratios = {
            45, 12,
            45, 20,
            20, 82,
            20, 97,
            
            80, 97,
            80, 82,
            55, 20,
            55, 12};
        double[] points = new double[ratios.length];
        
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0)
                points[i] = ratios[i] / 100.0 * scene.getWidth();
            else
                points[i] = ratios[i] / 100.0 * scene.getHeight();
        }
        Polyline p = new Polyline(points);
        
        int nodeCount = 0;
        for (int i = 1; i <= NUMBER_OF_NODE_ROWS; i++)
            nodeCount += i;
        Circle[] cs = new Circle[nodeCount];
        
        double ratio2Y = ratios[3];
        double ratio3Y = ratios[5];
        double vStart = ((ratio2Y + 2) / 100.0) * scene.getHeight();
        double vEnd = ((ratio3Y - 2) / 100.0) * scene.getHeight();
        double vSpan = vEnd - vStart;
        double vSpacing = vSpan / (NUMBER_OF_NODE_ROWS - 1);
        
        double x1 = ratios[2] / 100.0 * scene.getWidth();
        double y1 = ratios[3] / 100.0 * scene.getHeight();
        double x2 = ratios[4] / 100.0 * scene.getWidth();
        double y2 = ratios[5] / 100.0 * scene.getHeight();
        double m = (y1 - y2) / (x1 - x2);
        double b = y2 - m * x2;
        
        for (int i = 0, csIndex = 0; i < NUMBER_OF_NODE_ROWS; i++) {
            double y = vStart + vSpacing * i;
            
            double hStart = (y - b) / m;
            double hEnd = (scene.getWidth() / 2 - hStart) +
                    scene.getWidth() / 2;
            hStart += 5 / 100.0 * scene.getWidth();
            hEnd += -5 / 100.0 * scene.getWidth();
            
            double ratio2X = ratios[2];
            double ratio8X = ratios[14];
            double hSpan = hEnd - hStart;
            double hSpacing;
            if (i == 0) {
                hStart = (ratio2X + ratio8X) / 2 / 100.0 * scene.getWidth();
                hSpacing = 0;
            }
            else
                hSpacing = hSpan / i;
            
            for (int j = 0; j < i + 1; j++, csIndex++) {
                double x = hStart + hSpacing * j;
                
                cs[csIndex] = new Circle(x, y, 7);
                if (i >= 6) {
                    double ratio4Y = ratios[7];
                    root.getChildren().add(new Line(
                            x, y, x,
                            (ratio4Y / 100.0) * scene.getHeight()));
                }
            }
        }
        
        root.getChildren().add(p);
        root.getChildren().addAll(Arrays.asList(cs));
        
        primaryStage.setTitle("Exercise14_29");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
