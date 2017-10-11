/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter24;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_02 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView image;
                if ((int)(Math.random() * 2) == 0)
                    image = new ImageView("images/x.gif");
                else if ((int)(Math.random() * 2) == 1)
                    image = new ImageView("images/o.gif");
                else
                    continue;
                
                pane.add(image, j, i);
            }
        }
        
        Scene scene = new Scene(pane);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_02");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
