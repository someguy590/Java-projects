/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise18_26;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author someguy590
 */
public class Exercise18_26 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        final int NUMBER_OF_COLUMNS = 8;
        final int NUMBER_OF_ROWS = 8;
        Pane[][] maze = new Pane[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
        GridPane mazePane = new GridPane();
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Pane pane = new Pane();
                
                Rectangle r = new Rectangle(
                        pane.getWidth() / NUMBER_OF_COLUMNS,
                        pane.getHeight() / NUMBER_OF_ROWS);
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLACK);
                
                r.widthProperty().bind(mazePane.widthProperty().divide(NUMBER_OF_COLUMNS));
                r.heightProperty().bind(mazePane.heightProperty().divide(NUMBER_OF_ROWS));
                
                pane.getChildren().add(r);
                
                pane.setOnMouseClicked(e -> {
                    ArrayList<Node> lines = new ArrayList<>();
                    
                    for (Node node : pane.getChildren()) {
                        if (node instanceof Line)
                            lines.add(node);
                    }
                    
                    if (lines.isEmpty()) {
                        Line l1 = new Line(0, 0, pane.getWidth(), pane.getHeight());
                        Line l2 = new Line(pane.getWidth(), 0, 0, pane.getHeight());
                        pane.getChildren().addAll(l1, l2);
                        
                        l1.endXProperty().bind(pane.widthProperty());
                        l1.endYProperty().bind(pane.heightProperty());
                        l2.startXProperty().bind(pane.widthProperty());
                        l2.endYProperty().bind(pane.heightProperty());
                    }
                    else {
                        for (Node node : lines) {
                            if (node instanceof Line)
                                pane.getChildren().remove(node);
                        }
                    }
                    
                });
                
                maze[i][j] = pane;
                mazePane.add(pane, j, i);
            }
        }
        
        Button btnFindPath = new Button("Find Path");
        Button btnClearPath = new Button("Clear Path");
        HBox buttonPane = new HBox(btnFindPath, btnClearPath);
        buttonPane.setAlignment(Pos.CENTER);
        
        Label lblPathStatus = new Label();
        lblPathStatus.setAlignment(Pos.CENTER);
        lblPathStatus.setTextAlignment(TextAlignment.CENTER);
        HBox pathStatusPane = new HBox(lblPathStatus);
        pathStatusPane.setAlignment(Pos.CENTER);
        
        BorderPane root = new BorderPane();
        root.setCenter(mazePane);
        root.setTop(pathStatusPane);
        root.setBottom(buttonPane);
        
        btnFindPath.setOnAction(e -> {
            findPath(maze);
            setPathStatus(maze, lblPathStatus);
        });
        
        btnClearPath.setOnAction(e -> {
            for (Node node : mazePane.getChildren()) {
                if (node instanceof Pane) {
                    for (Node n : ((Pane)node).getChildren()) {
                        if (n instanceof Rectangle)
                            ((Rectangle)n).setFill(Color.TRANSPARENT);
                    }
                }
            }
            
            lblPathStatus.setText("");
        });      
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void findPath(Pane[][] maze) {
        findPath(maze, 0, 0);
    }
    
    public static void findPath(Pane[][] maze, int currentRow, int currentColumn) {
        Pane currentCell = maze[currentRow][currentColumn];
        
        // Check if first cell is free
        if (!isCellFree(currentCell))
            return;
        
        // Mark current cell
        paint(currentCell);
        
        // Stop search if no more free cells exist
        if (hasPathEnded(maze, currentRow, currentColumn))
            return;
        
        // Find next cell
        int[] nextCellIndex = getNextCell(maze, currentRow, currentColumn);
        findPath(maze, nextCellIndex[0], nextCellIndex[1]);
    }
    
    public static void setPathStatus(Pane[][] maze, Label lblPathStatus) {
        int mazeExitRow = maze.length - 1;
        int mazeExitColumn = maze[mazeExitRow].length - 1;
        Pane mazeExit = maze[mazeExitRow][mazeExitColumn];
        
        boolean isPathFound = false;
        for (Node node : mazeExit.getChildren()) {
            if (node instanceof Rectangle)
                isPathFound = !((Rectangle)node).getFill().equals(Color.TRANSPARENT);
        }
        
        if (isPathFound)
            lblPathStatus.setText("path found");
        else
            lblPathStatus.setText("path not found");
    }
    
    public static boolean isCellFree(Pane cell) {
        for (Node node : cell.getChildren()) {
            if (node instanceof Line)
                return false;
            
            if (node instanceof Rectangle) {
                if (!((Rectangle)node).getFill().equals(Color.TRANSPARENT))
                    return false;
            }
        }
        
        return true;
    }
    
    public static boolean hasPathEnded(Pane[][] maze, int currentRow, int currentColumn) {
        // Path ends if on maze exit
        if (currentRow == maze.length - 1 &&
                currentColumn == maze[currentRow].length - 1)
            return true;
        
        // Find any free, adjacent cell, if any
        if (currentRow - 1 >= 0 && 
                isCellFree(maze[currentRow - 1][currentColumn]))
            return false;
        if (currentRow + 1 < maze.length && 
                isCellFree(maze[currentRow + 1][currentColumn]))
            return false;
        if (currentColumn - 1 >= 0 && 
                isCellFree(maze[currentRow][currentColumn - 1]))
            return false;
        if (currentColumn + 1 < maze[currentRow].length &&
                 isCellFree(maze[currentRow][currentColumn + 1]))
            return false;
        
        return true;
    }
    
    public static int[] getNextCell(Pane[][] maze, int currentRow, int currentColumn) {
        // Find any free, adjacent cell, if any
        if (currentRow + 1 < maze.length && 
                isCellFree(maze[currentRow + 1][currentColumn]))
            return new int[]{currentRow + 1, currentColumn};
        if (currentColumn + 1 < maze[currentRow].length &&
                 isCellFree(maze[currentRow][currentColumn + 1]))
            return new int[]{currentRow, currentColumn + 1};        
        if (currentRow - 1 >= 0 && 
                isCellFree(maze[currentRow - 1][currentColumn]))
            return new int[]{currentRow - 1, currentColumn};
        if (currentColumn - 1 >= 0 && 
                isCellFree(maze[currentRow][currentColumn - 1]))
            return new int[]{currentRow, currentColumn - 1};
        
        return null;
    }
    
    private static void paint(Pane cell) {
        for (Node node : cell.getChildren()) {
            if (node instanceof Rectangle)
                ((Rectangle)node).setFill(Color.CADETBLUE);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
