/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter22_Algorithms;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class stuff extends Application {
    public static final int SIZE = 8; // The size of the chess board
    // Queens are placed at (i, queens[i])
    // -1 indicates that no queen is currently placed in the ith row
    // Initially, place a queen at (0,0) in the 0th row
    private int[] queens = {-1, -1, -1, -1, -1, -1, -1 , -1};
    
    @Override
    public void start(Stage primaryStage) {
        int solutionCount = 0;
        
        // Hold solutions
        HBox solutionPane = new HBox(10);

        while (search()) {
            solutionCount++;

            // Display chessboard
            GridPane chessBoard = new GridPane();
            chessBoard.setAlignment(Pos.CENTER);

            Label[][] labels = new Label[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    chessBoard.add(labels[i][j] = new Label(), j, i);
                    labels[i][j].setStyle("-fx-border-color: black");
                    labels[i][j].setPrefSize(55, 55);
                }
            }

            // Display queens
            Image image = new Image("image/queen.jpg");
            for (int i = 0; i < SIZE; i++) {
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(55);
                imageView.setFitWidth(55);
                labels[i][queens[i]].setGraphic(imageView);
            }

            // Add solution
            Label lblSolutionCount = new Label("Solution " + solutionCount, chessBoard);
            lblSolutionCount.setContentDisplay(ContentDisplay.BOTTOM);
            solutionPane.getChildren().add(lblSolutionCount);
        }

        // Display all solutions
        ScrollPane viewPane = new ScrollPane(solutionPane);

        // Create a scene and place it in the stage
        Scene scene = new Scene(viewPane, 55 * SIZE, 55 * SIZE);
        primaryStage.setTitle("Exercise 22_23"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    
    /** Search for a solution */
    private boolean search() {
        // k - 1 indicates the number of queens placed so far
        // We are looking for a position in the kth row to place a queen
        int k = 0;
        while (k >= 0 && k < SIZE) {
            // Find a position to place a queen in the kth row
            int j = findPosition(k);
            if (j < 0) {
                queens[k] = -1;
                k--;
            }
            else {
                queens[k] = j;
                k++;
            }
        }
        
        if (k == -1)
            return false; // No solution
        else
            return true; // A solution is found
    }
    
    public int findPosition(int k) {
        int start = queens[k] + 1; // Search for a new placement
        
        for (int j = start; j < SIZE; j++) {
            if (isValid(k,j))
                return j; // (k, j) is the place to put the queen now
        }
        
        return -1;
    }
    
    /** Return true if a queen can be placed at (row, column) */
    public boolean isValid(int row, int column) {
        for (int i = 1; i <= row; i++)
            if (queens[row - i] == column // Check column
                || queens[row - i] == column - i // Check upleft diagonal
                || queens[row - i] == column + i) // Check upleft diagonal
                return false;
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}