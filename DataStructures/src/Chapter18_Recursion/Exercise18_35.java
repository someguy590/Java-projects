/*
 * Author: Jonathan Moyett
 * CSCI 2410
 * Date: January 23, 2015
 */
package Chapter18_Recursion;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_35 extends Application {

    @Override
    public void start(Stage primaryStage) {
        HTreePane hTreePane = new HTreePane();
        TextField tfOrder = new TextField();
        tfOrder.setOnAction(
                e -> hTreePane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

        // Pane to hold label and a text field
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hTreePane);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 210);
        primaryStage.setTitle("Exercise18_35: H-tree fractal");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Resize fractal upon change 
        scene.widthProperty().addListener(ov -> hTreePane.paint());
        scene.heightProperty().addListener(ov -> hTreePane.paint());
    }

    /**
     * Pane for displaying H-tree
     */
    static class HTreePane extends Pane {
        private int order = 0;
        
        /**
         * Set a new order
         */
        public void setOrder(int order) {
                this.order = order;
                paint();
        }

        HTreePane() {
        }

        protected void paint() {
            // Find center in proportion to the pane size
            Point2D paneCenter = new Point2D(getWidth() / 2, getHeight() / 2);
            double lineLength = Math.min(getWidth() / 3, getHeight() / 3); // Make lines roughly a third of the window

            this.getChildren().clear(); // Clear the pane before redisplay

            displayHTree(order, paneCenter, lineLength);
        }
        
        
        private void displayHTree(int order, Point2D center, double lineLength) {
            double paneXCenter = center.getX();
            double paneYCenter = center.getY();

            Point2D p1 = new Point2D(paneXCenter - lineLength / 2, paneYCenter - lineLength / 2);
            Point2D p2 = new Point2D(paneXCenter - lineLength / 2, paneYCenter + lineLength / 2);
            Point2D p3 = new Point2D(paneXCenter + lineLength / 2, paneYCenter - lineLength / 2);
            Point2D p4 = new Point2D(paneXCenter + lineLength / 2, paneYCenter + lineLength / 2);
            
            // Draw three lines in an H-shape
            getChildren().add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
            getChildren().add(new Line(p3.getX(), p3.getY(), p4.getX(), p4.getY()));
            getChildren().add(new Line(paneXCenter - lineLength / 2, paneYCenter, paneXCenter + lineLength / 2, paneYCenter));

            if (order > 0) {
                displayHTree(order - 1, p1, lineLength / 2);
                displayHTree(order - 1, p2, lineLength / 2);
                displayHTree(order - 1, p3, lineLength / 2);
                displayHTree(order - 1, p4, lineLength / 2);
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}