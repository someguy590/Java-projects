/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Project8 extends Application {
    public static Stack<Character> operators = new Stack<>();
    @Override
    public void start(Stage primaryStage) {
        
        // Top half of pane - text
        Label lblInput = new Label("Input");
        Label lblStack = new Label("Stack");
        Label lblOutput = new Label("Output");
        
        TextField txtInput = new TextField();
        TextField txtStack = new TextField();
        TextField txtOutput = new TextField();
        
        GridPane gridPaneTextFields = new GridPane();
        gridPaneTextFields.add(lblInput, 0, 0);
        gridPaneTextFields.add(txtInput, 1, 0);
        
        gridPaneTextFields.add(lblStack, 0, 1);
        gridPaneTextFields.add(txtStack, 1, 1);
        
        gridPaneTextFields.add(lblOutput, 0, 2);
        gridPaneTextFields.add(txtOutput, 1, 2);
        
        
        // Bottom half of pane - buttons
        Button[] btnChars = {new Button("a"), new Button("b"), new Button("c"), new Button("d")};
        
        char chars[] = {'a', 'b', 'c', 'd'};
        for (int i = 0; i < btnChars.length; i++) {
            btnChars[i].setOnAction(e -> {
                txtInput.setText(txtInput.getText());
            });
        }
        Button[][] buttons = {
            {new Button("a"), new Button("b"), new Button("c"), new Button("d")},
            {new Button("+"), new Button("-"), new Button("*"), new Button("/")},
            {new Button("("), new Button(")"), new Button("clear"), new Button("exit")}      
        };
        
        GridPane gridPaneButtons = new GridPane();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                gridPaneButtons.add(buttons[i][j], j, i);
            }
        }
        
        VBox root = new VBox();
        root.getChildren().addAll(gridPaneTextFields, gridPaneButtons);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void addOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
                ch == '(' || ch == ')')
            operators.add(ch);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
