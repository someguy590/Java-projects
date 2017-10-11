/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class TestFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        
        Button btn = new Button("Hello World!");
        btn.setOnAction(e -> { System.out.println("ayy lamo");
        });
        
        Button btn2 = new Button("Trigger");
        btn2.setOnAction(e -> { btn.fire(); });
        
        root.getChildren().addAll(btn, btn2);
        
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Hello World!");
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
