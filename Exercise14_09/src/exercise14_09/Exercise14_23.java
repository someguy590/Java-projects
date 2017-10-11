/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise14_09;

import java.util.Arrays;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_23 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Scanner input = new Scanner(System.in);
        
        Rectangle[] rs = new Rectangle[2];
        double[][] centers = new double[2][2];
        
        double cx1 = 200;
        double cy1 = 200;
        
        double cx2 = 200;
        double cy2 = 500;
        
        double w1 = 250;
        double h1 = 200;
        
        double w2 = 225;
        double h2 = 128;
        
        centers[0][0] = cx1;
        centers[0][1] = cy1;
        centers[1][0] = cx2;
        centers[1][1] = cy2;
        rs[0] = new Rectangle(w1, h1);
        rs[1] = new Rectangle(w2, h2);
        
        double xDistance = centers[0][0] > centers[1][0] ?
                centers[0][0] - centers[1][0] : centers[1][0] - centers[0][0];
        double yDistance = centers[0][1] > centers[1][1] ?
                centers[0][1] - centers[1][1] : centers[1][1] - centers[0][1];
        
        
        adjustCenters(centers, rs);
        for (int i = 0; i < rs.length; i++) {
            rs[i].setX(centers[i][0]);
            rs[i].setY(centers[i][1]);
            rs[i].setFill(Color.TRANSPARENT);
            rs[i].setStroke(Color.ROSYBROWN);
        }
        
        String message;
        if (xDistance <= (rs[0].getWidth() - rs[1].getWidth()) / 2 &&
                yDistance <= (rs[0].getHeight() - rs[1].getHeight()) / 2)
            message = "One rectangle is contained in another";
        else if (xDistance <= (rs[0].getWidth() + rs[1].getWidth()) / 2 &&
                yDistance <= (rs[0].getHeight()+ rs[1].getHeight()) / 2)
            message = "The rectangles overlap";
        else
            message = "The rectangles do not overlap";
        
        Pane rPane = new Pane();
        Label lblMessage = new Label(message);
        lblMessage.setAlignment(Pos.BASELINE_CENTER);
        BorderPane root = new BorderPane(rPane, null, null, lblMessage, null);
        rPane.getChildren().addAll(Arrays.asList(rs));
        
        Circle c1 = new Circle(cx1, cy1, 10);
        Circle c2 = new Circle(cx2, cy2, 10);
        rPane.getChildren().addAll(c1, c2);
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void adjustCenters(double[][] centers, Rectangle[] rectangles) {
        for (int i = 0; i < centers.length; i++) {
            centers[i][0] = centers[i][0] - rectangles[i].getWidth() / 2;
            centers[i][1] = centers[i][1] - rectangles[i].getHeight()/ 2;
        }
        System.out.println(Arrays.deepToString(centers));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
