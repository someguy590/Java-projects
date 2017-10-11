/**
 * Created by someguy590 on 12/21/2016.
 */

import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final int GRID_SIZE = 8;
        XPane[][] xPanes = new XPane[GRID_SIZE][GRID_SIZE];

        // grid
        GridPane gridPane = new GridPane();
        for (int i = 0; i < xPanes.length; i++) {
            for (int j = 0; j < xPanes[i].length; j++) {
                xPanes[i][j] = new XPane();
                xPanes[i][j].minWidthProperty().bind(gridPane.widthProperty().divide(GRID_SIZE));
                xPanes[i][j].minHeightProperty().bind(gridPane.heightProperty().divide(GRID_SIZE));
                gridPane.add(xPanes[i][j], j, i);
            }
        }

        // buttons
        Button btnFindPath = new Button("Find Path");
        Button btnClearPath = new Button("Clear Path");


        HBox hBoxButtons = new HBox(btnFindPath, btnClearPath);
        hBoxButtons.setAlignment(Pos.CENTER);

        // path found message
        Label lblPathresult = new Label("");
        StackPane displayPathResult = new StackPane(lblPathresult);

        // root pane and set scene
        VBox root = new VBox();
        root.getChildren().addAll(displayPathResult, gridPane, hBoxButtons);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // button actions
        btnFindPath.setOnMouseClicked(event -> {
            lblPathresult.setText("clicked on find path button");
        });

        btnClearPath.setOnMouseClicked(event -> {
            for (XPane[] xPaneRows : xPanes) {
                for (XPane xPane : xPaneRows)
                    xPane.getChildren().clear();
            }
        });
    }
}
