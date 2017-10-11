/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise14_06;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_06 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        
        double size;
        if (root.getWidth() < root.getHeight())
            size = root.getWidth() / 8;
        else
            size = root.getHeight() / 8;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(size, size);
                if ((i + j) % 2 == 1)
                    root.add(r, j, i);
            }
        }
        
        // Width changes
        root.widthProperty().addListener(l -> {
            root.getChildren().clear();
            
            double sideLength;
            if (root.getWidth() < root.getHeight())
                sideLength = root.getWidth() / 8;
            else
                sideLength = root.getHeight() / 8;
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 1) {
                        Rectangle r = new Rectangle(sideLength, sideLength);
                        root.add(r, j, i);
                    }
                }
            }
        });
        
        // Height Changes
        root.heightProperty().addListener(l -> {
            root.getChildren().clear();
            
            double sideLength;
            if (root.getHeight() < root.getWidth())
                sideLength = root.getHeight() / 8;
            else
                sideLength = root.getWidth() / 8;
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 1) {
                        Rectangle r = new Rectangle(sideLength, sideLength);
                        root.add(r, j, i);
                    }
                }
            }
        });
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Exercise14_06");
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
