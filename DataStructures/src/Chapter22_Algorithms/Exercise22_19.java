/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter22_Algorithms;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *
 * @author jm4386
 */
public class Exercise22_19 extends Application {
    private final int SIZE = 10;
    private TextField[][] numbers = new TextField[SIZE][SIZE];
            
    @Override
    public void start(Stage primaryStage) {
        // Grid pane holding matrix
        GridPane matrixPane = new GridPane();
        matrixPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrixPane.add(numbers[i][j] = new TextField(Integer.toString((int)(Math.random() * 2))), j, i);
                numbers[i][j].setAlignment(Pos.CENTER);
                //numbers[i][j].setPrefColumnCount(1);
            }
        }
        
        // Refresh button
        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");
        btnRefresh.setAlignment(Pos.CENTER);
        btnRefresh.setOnAction(e -> {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    numbers[i][j].setText(Integer.toString((int)(Math.random() * 2)));
                }
            }
        });
        
        // Find largest block button
        Button btnFind = new Button();
        btnFind.setText("Find Largest Block");
        btnFind.setOnAction(e -> {
            findLargestBlock(numbers);
        });

        // Pane for buttons
        HBox paneForBtn = new HBox(10, btnRefresh, btnFind);
        paneForBtn.setAlignment(Pos.CENTER);
        
        // Root pane holding matrix pane and buttons
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(matrixPane);
        borderPane.setBottom(paneForBtn);
        
        // Ready and display scene
        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setTitle("Exercise22_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void findLargestBlock(TextField[][] m) {
        int dimension = 1;
        int currentGroup = 0;
        String prevNumber = "0";
        int[] currentLargestBlock = {0, 0, 0}; // (row index, column index, row length)
        int[][] groups = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
        int[][] currentRow = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
        
        // Initialize groups
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (m[i][j].getText().equals("1")) {
                    prevNumber = "1";
                    groups[currentGroup][0] = i;
                    groups[currentGroup][1] = j;
                    groups[currentGroup][2] = i;
                    groups[currentGroup][3] = j;
                    groups[currentGroup][4]++;
                    if (j == SIZE - 1)
                        groups[currentGroup][1] = groups[currentGroup][1] - (groups[currentGroup][4] - 1);
                }
                else if (!m[i][j].getText().equals("1") && prevNumber.equals("1")) {
                    groups[currentGroup][1] = groups[currentGroup][1] - (groups[currentGroup][4] - 1);
                    currentGroup++;
                    prevNumber = m[i][j].getText();
                }
            }
            
            
            
            for (int j = 0; j < groups.length; j++) {
                if (groups[j][4] < dimension)
                    j++;
                
            }
        }
        
        // Start search for blocks
        for (int i = 1; i < 1; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (m[i][j].getText().equals("1")) {
                    prevNumber = "1";
                    currentRow[currentGroup][0] = i;
                    currentRow[currentGroup][1] = j;
                    currentRow[currentGroup][2] = i;
                    currentRow[currentGroup][3] = j;
                    currentRow[currentGroup][4]++;
                    if (j == SIZE - 1)
                        currentRow[currentGroup][1] = currentRow[currentGroup][1] - (currentRow[currentGroup][4] - 1);
                }
                else if (!m[i][j].getText().equals("1") && prevNumber.equals("1")) {
                    currentRow[currentGroup][1] = currentRow[currentGroup][1] - (currentRow[currentGroup][4] - 1);
                    currentGroup++;
                    prevNumber = m[i][j].getText();
                }
                
            }
            
            for (int j = 0; j < currentRow.length; j++) {
                if (currentRow[j][4] < dimension)
                    j++;
                
                
            }
            
            
            
        }
        currentLargestBlock[0] = currentRow[0][0];
        currentLargestBlock[1] = currentRow[0][1];
        currentLargestBlock[2] = currentRow[0][4];
        paintBlock(numbers, currentLargestBlock[0], currentLargestBlock[1], currentLargestBlock[2]);
    }
    
    public int[] checkForLargerBlock(final int[][] m) {
        
        return m[0];
    }

    public void paintBlock(TextField[][] m, int rowIndex, int columnIndex, int rows) {
        // Clear matrix of previous solution
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j].setStyle("-fx-control-inner-background: white");
            }
        }
        
        // Color largest block
        for (int i = rowIndex; i < rowIndex + rows; i++) {
            for (int j = columnIndex; j < columnIndex + rows; j++) {
                m[i][j].setStyle("-fx-background-color: red");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}