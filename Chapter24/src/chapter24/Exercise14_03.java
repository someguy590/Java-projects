/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter24;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise14_03 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        File file = new File("src/images/card");
        int deckSize = 0;
        for (File f : file.listFiles()) {
            if (f.getName().matches("[0-9]+\\.png")) {
                System.out.println(f.getName());
                deckSize++;
            }
        }
        
        Integer[] cardNumbers = new Integer[deckSize];
        for (int i = 0; i < cardNumbers.length; i++)
            cardNumbers[i] = i + 1;
        shuffle(cardNumbers);
        
        VBox cardPane = new VBox();
        ScrollPane viewPane = new ScrollPane();
        viewPane.setContent(cardPane);
        
        int i = 0;
        while (i < cardNumbers.length) {
            HBox rowOfCardsPane = new HBox(10);
            int j = 0;
            while (j < 9 && i < cardNumbers.length) {
                ImageView image = new ImageView(
                        "images/card/" + cardNumbers[i] + ".png");
                rowOfCardsPane.getChildren().add(image);
                i++;
                j++;
            }
            cardPane.getChildren().add(rowOfCardsPane);
        }
        
        Scene scene = new Scene(viewPane, 300, 250);
        
        primaryStage.setTitle("Exercise14_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static <T> void shuffle(T[] a) {
        for (int i = 0; i < a.length; i++) {
            // Pick new spot for current element
            int newSpot = (int)(Math.random() * 52);
            
            if (newSpot == i)
                continue;
            
            // Save element in replacement spot
            T temp = a[newSpot];
            a[newSpot] = a[i];
            a[i] = temp;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
