/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise14_05;

import com.sun.glass.ui.Pixels;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_05 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String word = "Welcome To Java";
        
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        
        double radius;
        if (pane.getWidth() < pane.getHeight())
            radius = pane.getWidth() / 2 - 20;
        else
            radius = pane.getHeight() / 2 - 20;
        
        for (int i = 0; i < word.length(); i++) {
            double angleD = 1.0 / word.length() * 360 * i;
            double angleRad = angleD * Math.PI / 180;
            
            Text text = new Text(String.valueOf(word.charAt(i)));
            text.xProperty().bind(pane.widthProperty().divide(2).add(radius * Math.cos(angleRad)));
            text.yProperty().bind(pane.heightProperty().divide(2).add(radius * Math.sin(angleRad)));
            text.setRotate(angleD + 90);
            pane.getChildren().add(text);
        }
        
        primaryStage.setTitle("Exercise14_05"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
